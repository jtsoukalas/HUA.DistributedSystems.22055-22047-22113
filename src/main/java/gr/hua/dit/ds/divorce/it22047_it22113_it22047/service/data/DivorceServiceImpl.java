package gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.DivorceAPIRequest;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceStatusException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.FewerDivorceStatementsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceStatementRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.email.EmailOption;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class DivorceServiceImpl implements DivorceService {

    @Autowired
    DivorceRepository divorceRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    DivorceStatementRepository divorceStatementRepo;

    @Autowired
    DivorceDAO divorceDAO;

    @Autowired
    EmailSenderService emailSenderService;

    /**
     * {@inheritDoc}
     */
    public User checkRole(Integer taxNumber, Faculty faculty) throws UserNotFoundException, UserWithWrongRoleException {
        User user = userRepo.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new UserNotFoundException(taxNumber));
        if (!user.getRoles().contains(faculty.getRole())) {
            throw new UserWithWrongRoleException(taxNumber, faculty);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public Divorce prepareDivorce(DivorceAPIRequest divorceData) throws UserNotFoundException, UserWithWrongRoleException {
        Divorce divorce = new Divorce();

        divorce.setStatement(prepareDivorceStatements(divorceData, divorce));
        divorce.setLawyerLead(checkRole(divorceData.getLawyerLeadTaxNumber(), Faculty.LAWYER_LEAD));
        divorce.setStatus(divorceData.getStatus());
        divorce.setContractDetails(divorceData.getContractDetails());

        return divorce;
    }


    /**
     * {@inheritDoc}
     */
    public List<DivorceStatement> prepareDivorceStatements(DivorceAPIRequest divorceData, Divorce divorce) throws UserNotFoundException, UserWithWrongRoleException {
        List<DivorceStatement> divorceStatements = new ArrayList<>();
        HashMap<Faculty, User> involvedParties = prepareInvolvedParties(divorceData);

        for (Map.Entry<Faculty, User> entry : involvedParties.entrySet()) {
            Faculty faculty = entry.getKey();
            User user = entry.getValue();
            divorceStatements.add(prepareDivorceStatement(user, divorce, faculty));
        }

        return divorceStatements;
    }

    @Override
    public DivorceStatement prepareDivorceStatement(User user, Divorce divorce, Faculty faculty) throws UserNotFoundException, UserWithWrongRoleException {
        return new DivorceStatement(checkRole(user.getTaxNumber(), faculty), faculty, divorce);
    }

    /**
     * {@inheritDoc}
     */
    public HashMap<Faculty, User> prepareInvolvedParties(DivorceAPIRequest divorceData) throws UserNotFoundException, UserWithWrongRoleException {
        HashMap<Faculty, User> involvedParties = new HashMap<>();
        try {
            involvedParties.put(Faculty.LAWYER_LEAD, checkRole(divorceData.getLawyerLeadTaxNumber(), Faculty.LAWYER_LEAD));
        } catch (NullPointerException ignored) {
        }
        try {
            involvedParties.put(Faculty.LAWYER_TWO, checkRole(divorceData.getLawyerTwoTaxNumber(), Faculty.LAWYER_TWO));
        } catch (NullPointerException ignored) {
        }
        try {
            involvedParties.put(Faculty.SPOUSE_ONE, checkRole(divorceData.getSpouseOneTaxNumber(), Faculty.SPOUSE_ONE));
        } catch (NullPointerException ignored) {
        }
        try {
            involvedParties.put(Faculty.SPOUSE_TWO, checkRole(divorceData.getSpouseTwoTaxNumber(), Faculty.SPOUSE_TWO));
        } catch (NullPointerException ignored) {
        }
        try {
            involvedParties.put(Faculty.NOTARY, checkRole(divorceData.getNotaryTaxNumber(), Faculty.NOTARY));
        } catch (NullPointerException ignored) {
        }
        return involvedParties;
    }

    /**
     * {@inheritDoc}
     */
    public Divorce createDivorce(DivorceAPIRequest divorceData) throws FewerDivorceStatementsException, DivorceStatusException, UserNotFoundException, UserWithWrongRoleException {
        Divorce divorce = DivorceService.checkDivorce(prepareDivorce(divorceData));
        divorce.setApplicationDate(new Date());
        return saveDivorce(divorce);
    }

    /**
     * {@inheritDoc}
     */
    public Divorce editDivorce(DivorceAPIRequest divorceData) throws DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, FewerDivorceStatementsException {
        Divorce divorce;
        if (divorceData.getId() == null) {
            throw new IllegalArgumentException("Divorce id cannot be null");
        } else {
            divorce = divorceRepo.findById(divorceData.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Divorce with id " + divorceData.getId() + " does not exist"));
        }

        boolean draftToDraft = divorce.getStatus().equals(DivorceStatus.DRAFT) && divorceData.getStatus().equals(DivorceStatus.DRAFT);
        boolean draftToPending = divorce.getStatus().equals(DivorceStatus.DRAFT) && divorceData.getStatus().equals(DivorceStatus.PENDING);
        boolean pendingToPending = divorce.getStatus().equals(DivorceStatus.PENDING) && divorceData.getStatus().equals(DivorceStatus.PENDING);

        if (!draftToDraft && !draftToPending && !pendingToPending) {
            throw new DivorceStatusException("Divorce status change from " + divorce.getStatus() + " to " + divorceData.getStatus() + " is not allowed");
        }

        for (DivorceStatement statement : divorce.getStatement()) {
            if (!statement.getPerson().getTaxNumber().equals(divorceData.getUserTaxNumber(statement.getFaculty()))) {
                prepareDivorceStatement(statement.getPerson(), divorce, statement.getFaculty());
                if (draftToPending) {
                    emailSenderService.emailParty(divorce, statement.getFaculty(), EmailOption.NEW_DIVORCE);
                } else if (pendingToPending) {
                    throw new DivorceStatusException("Changes to involved parties are not allowed when divorce is pending. To do that you need to cancel the divorce and start a new one");
                }
            }
        }

        if (draftToPending) {
            if (divorceData.getContractDetails() == null) {
                throw new DivorceStatusException("Contract details cannot be null when changing divorce status from draft to pending");
            }
            divorce.setContractDetails(divorceData.getContractDetails());
        }

        divorce.setStatus(divorceData.getStatus());
        divorce.setContractDetails(divorceData.getContractDetails());
        divorce.setApplicationDate(new Date());
        return saveDivorce(DivorceService.checkDivorce(divorce));
    }

    /**
     * {@inheritDoc}
     */
    public Divorce saveDivorce(Divorce divorceData) {
        Divorce divorce = divorceRepo.save(divorceData);
        if (divorce.getStatus().equals(DivorceStatus.DRAFT)) {
            divorce.getLawyerLead().addDivorce(divorce);
        } else {
            if (divorce.getStatement() != null) {
                for (DivorceStatement statement : divorce.getStatement()) {
                    statement.getPerson().addDivorce(divorce);
                    userRepo.save(statement.getPerson());
                }
            }
        }
        return divorce;
    }
}

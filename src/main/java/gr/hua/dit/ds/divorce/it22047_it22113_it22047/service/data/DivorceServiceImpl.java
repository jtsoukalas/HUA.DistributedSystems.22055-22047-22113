package gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.DivorceAPIRequest;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceStatusException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.FewerDivorceStatementsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.SimilarDivorceExistsException;
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
        if (!user.getRole().equals(faculty.getRole())) {
            throw new UserWithWrongRoleException(taxNumber, faculty);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public Divorce prepare(DivorceAPIRequest divorceData) throws UserNotFoundException, UserWithWrongRoleException {
        Divorce divorce = new Divorce();

        divorce.setStatus(divorceData.getStatus());
        divorce.setStatements(prepareDivorceStatements(divorceData, divorce));
        divorce.setLawyerLead(checkRole(divorceData.getLawyerLeadTaxNumber(), Faculty.LAWYER_LEAD));
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
    public Divorce create(DivorceAPIRequest divorceData) throws FewerDivorceStatementsException, DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, SimilarDivorceExistsException {
        Divorce divorce = checkBeforePublishing(prepare(divorceData));
        divorce.setApplicationDate(new Date());
        return save(divorce);
    }

    /**
     * {@inheritDoc}
     */
    public Divorce edit(DivorceAPIRequest divorceData) throws DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, FewerDivorceStatementsException, SimilarDivorceExistsException {
        Divorce divorce;
        if (divorceData.getId() == null) {
            throw new IllegalArgumentException("Divorce id cannot be null");
        } else {
            divorce = divorceRepo.findById(divorceData.getId())
                    .orElseThrow(() -> new DivorceNotFoundException(divorceData));
        }

        boolean draftToDraft = divorce.getStatus().equals(DivorceStatus.DRAFT) && divorceData.getStatus().equals(DivorceStatus.DRAFT);
        boolean draftToPending = divorce.getStatus().equals(DivorceStatus.DRAFT) && divorceData.getStatus().equals(DivorceStatus.PENDING);
        boolean pendingToPending = divorce.getStatus().equals(DivorceStatus.PENDING) && divorceData.getStatus().equals(DivorceStatus.PENDING);

        if (!draftToDraft && !draftToPending && !pendingToPending) {
            throw new DivorceStatusException("Divorce status change from " + divorce.getStatus() + " to " + divorceData.getStatus() + " is not allowed");
        }

        if (!divorce.getLawyerLead().getTaxNumber().equals(divorceData.getLawyerLeadTaxNumber())) {
            throw new DivorceStatusException("Changes to lead lawyer are not allowed. To do that you need to cancel the divorce and start a new one");
        }

        //Check given divorce statements
        if (divorceData.getSpouseOneTaxNumber().equals(divorceData.getSpouseTwoTaxNumber())) {
            throw new IllegalArgumentException("Spouse one and spouse two cannot be the same person");
        }
        if (divorceData.getNotaryTaxNumber().equals(divorceData.getSpouseOneTaxNumber())
                || divorceData.getNotaryTaxNumber().equals(divorceData.getSpouseTwoTaxNumber())) {
            throw new IllegalArgumentException("Notary cannot be the same person as spouse");
        }

        if (!divorce.getContractDetails().equals(divorceData.getContractDetails())) {
            divorce.getStatements().forEach(statement -> {
                User user = userRepo.findByTaxNumber(statement.getPerson().getTaxNumber())
                        .orElse(null)
                        .removeDivorce(divorce);
                userRepo.save(user);
            });
            userRepo.save(userRepo.findByTaxNumber(divorce.getLawyerLead().getTaxNumber())
                    .orElse(null)
                    .removeDivorce(divorce));
            List<DivorceStatement> statements = divorce.getStatements();
            divorce.removeAllDivorceStatements();
            divorceStatementRepo.deleteAll(statements);
        }

        List<Faculty> facultiesToCheck = Faculty.getFaculties();
        facultiesToCheck.remove(Faculty.LAWYER_LEAD);

        //Check statements by existing divorce
        List<DivorceStatement> statementsToAdd = new ArrayList<>();
        List<DivorceStatement> statementsToRemove = new ArrayList<>();
        for (DivorceStatement oldStatement : divorce.getStatements()) {
            facultiesToCheck.remove(oldStatement.getFaculty());

            Integer newStatementTaxNumber = divorceData.getUserTaxNumber(oldStatement.getFaculty());
            if (newStatementTaxNumber == null) {
                userRepo.save(oldStatement.getPerson().removeDivorce(divorce));
                divorceStatementRepo.delete(oldStatement);
            } else if (!oldStatement.getPerson().getTaxNumber().equals(newStatementTaxNumber)) {
                statementsToAdd.add(divorceStatementRepo.save(prepareDivorceStatement(
                        userRepo.findByTaxNumber(newStatementTaxNumber)
                                .orElseThrow(() -> new UserNotFoundException(newStatementTaxNumber)),
                        divorce, oldStatement.getFaculty())));
                userRepo.save(oldStatement.getPerson().removeDivorce(divorce));
                statementsToRemove.add(oldStatement);
            }
        }

        divorce.addAllDivorceStatement(statementsToAdd);

        divorce.removeAllDivorceStatement(statementsToRemove);
        statementsToRemove.forEach(statement -> {
            divorceStatementRepo.delete(statement);
        });

        if (draftToPending) {
            checkBeforePublishing(divorce);
            divorce.setContractDetails(divorceData.getContractDetails());
            publish(divorce);
        }

        divorce.setStatus(divorceData.getStatus());
        divorce.setContractDetails(divorceData.getContractDetails());
        divorce.setApplicationDate(new Date());
        Divorce savedDivorce = save(divorce, true);

        //Check for new statements from divorceData
        for (Faculty faculty : facultiesToCheck) {
            Integer userTaxNumber = divorceData.getUserTaxNumber(faculty);
            statementsToAdd.add(divorceStatementRepo.save(prepareDivorceStatement(
                    userRepo.findByTaxNumber(userTaxNumber)
                            .orElseThrow(() -> new UserNotFoundException(userTaxNumber)),
                    savedDivorce, faculty)));
        }
        divorce.addAllDivorceStatement(statementsToAdd);
        return  save(divorce,true);
    }

    /**
     * Save divorce to db. Checks for similar divorce before.
     *
     * @param divorceData divorce to save
     */
    private Divorce save(Divorce divorceData) throws SimilarDivorceExistsException {
        return save(divorceData, false);
    }

    /**
     * Save divorce to db. Does not check for similar divorce.
     *
     * @param divorceData divorce to save
     */
    private Divorce save(Divorce divorceData, boolean editMode) throws SimilarDivorceExistsException {
        if (!editMode) {
            Divorce similarDivorce = checkIfDivorceExists(divorceData);
            if (similarDivorce != null) {
                throw new SimilarDivorceExistsException(similarDivorce);
            }
        }
        Divorce divorce = divorceRepo.saveAndFlush(divorceData);
        userRepo.save(divorce.getLawyerLead().addDivorce(divorce));
        if (!divorce.getStatus().equals(DivorceStatus.DRAFT)) {
            if (divorce.getStatements() != null) {
                for (DivorceStatement statement : divorce.getStatements()) {
                    userRepo.save(statement.getPerson().addDivorce(divorce));
                }
            }
        }
        return divorce;
    }

    /**
     * {@inheritDoc}
     */
    public Divorce checkIfDivorceExists(Divorce divorce) {
        User spouseOne = divorce.getSpouseOne();
        User spouseTwo = divorce.getSpouseTwo();
        return divorce.getLawyerLead().getDivorces().stream().filter(d -> {
            try{
            if (d.checkIfUserIsSpouse(spouseOne) && d.checkIfUserIsSpouse(spouseTwo)
                    && !d.isClosed() && d.getLawyerLead().equals(divorce.getLawyerLead())) {
                return true;
            }}catch (Exception ignored){}
            return false;
        }).findFirst().orElse(null);
    }

    public Divorce checkBeforePublishing(Divorce divorce) throws FewerDivorceStatementsException, DivorceStatusException, SimilarDivorceExistsException {
        //Check if status is valid
        if (divorce.isClosed()) {
            throw new DivorceStatusException("Divorce status set to " + divorce.getStatus().toHumanReadable() + " is not valid.");
        }

        //Check if there are duplicate persons in statements
        if (divorce.getSpouseOne().equals(divorce.getSpouseTwo())) {
            throw new FewerDivorceStatementsException("Spouse one and spouse two can not be set the same person.");
        }
        if (divorce.getSpouseOne().equals(divorce.getNotary()) || divorce.getSpouseTwo().equals(divorce.getNotary())) {
            throw new FewerDivorceStatementsException("Spouse one and notary can not be set the same person.");
        }
        if (divorce.getLawyerLead().equals(divorce.getNotary()) || divorce.getLawyerTwo().equals(divorce.getNotary())) {
            throw new FewerDivorceStatementsException("Lawyers can not be also set as notary.");
        }

        //Check that all statements are present if status is not draft
        if (!divorce.getStatus().equals(DivorceStatus.DRAFT)) {
            List<Faculty> faculties = Faculty.getFaculties();
            for (DivorceStatement divorceStatement : divorce.getStatements()) {
                if (!faculties.contains(divorceStatement.getFaculty())) {
                    throw new FewerDivorceStatementsException("Divorce statement with faculty: " + divorceStatement.getFaculty().name() + " not found.");
                } else {
                    faculties.remove(divorceStatement.getFaculty());
                }
            }
        }

        //Check if contract details are present
        if (divorce.getContractDetails() == null) {
            throw new DivorceStatusException("Contract details cannot be null when changing divorce status from draft to pending");
        }

        return divorce;
    }

    /**
     * Making necessary actions for publishing a divorce. Doesn't make any checks.
     * Divorce statements should already be prepared.
     *
     * @param divorce
     */
    private void publish(Divorce divorce) {
        divorce.getStatements().forEach(statement -> {
            if (!statement.getFaculty().equals(Faculty.NOTARY)) {
                statement.setChoice(DivorceStatementChoice.PENDING);
            }
        });
        emailSenderService.emailParties(divorce, EmailOption.NEW_DIVORCE);
    }
}

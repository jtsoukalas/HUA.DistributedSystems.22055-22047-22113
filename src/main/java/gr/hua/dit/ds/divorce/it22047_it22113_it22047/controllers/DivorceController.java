package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceStatementRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/divorce")
public class DivorceController {

    @Autowired
    DivorceRepository divorceRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    DivorceStatementRepository divorceStatementRepo;

    @Autowired
    DivorceDAO divorceDAO;

    /**
     * Returns divorces of the user with the given tax number
     * @param taxNumber we might want to remove the param after security is implemented
     * @return List of divorces
     */
    @GetMapping("/myDivorces")
//    @PreAuthorize("hasRole('LAWYER') or hasRole('NOTARY') or hasRole('SPOUSE')")
    public List<DivorceAPIResponseConcise> myDivorces(Integer taxNumber) {
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces().stream().map(d-> new DivorceAPIResponseConcise(d)).collect(Collectors.toList());
    }

    /**
     * Returns divorce details for the given id
     * @param id
     * @return
     * @throws NoSuchElementException is id is not found
     */
    @GetMapping("/findById")
    public DivorceAPIResponse findById(Integer id) throws NoSuchElementException {
        //Check access to divorce
        return new DivorceAPIResponse(divorceRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Divorce with id " + id + " not found")));
    }

    /**
     * Returns divorces that mach the given criteria
     * @param query divorce query
     * @param taxNumber we might want to remove the param after security is implemented
     * @return
     */
    @GetMapping("/search")
    public List<DivorceAPIResponseConcise> search (String query, Integer taxNumber) {
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces().stream().filter(d-> d.search(query))
                .map(d-> new DivorceAPIResponseConcise(d)).collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    public List<Divorce> findAll() {
        return divorceRepo.findAll();
    }

    @GetMapping("/adminFind")
//        @PreAuthorize("hasRole('ADMIN')")
    public List<Divorce> findByTaxNumber(Integer taxNumber) {
//        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new UsernameNotFoundException("User with tax number " + taxNumber + " not found"))
//                .getCases();  //fixme security
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces();
    }

    @GetMapping("/findByTaxNumber")
//    @PreAuthorize("hasRole('LAWYER') or hasRole('NOTARY') or hasRole('SPOUSE')")
    public List<Divorce> findByTaxNumber(Integer senderTaxNumber, Integer taxNumber) {
        //1. todo security check if taxNumber of auth user ,is the same as the one in the request.taxNumber or is an admin
        List<Divorce> divorces = userRepo.findByTaxNumber(senderTaxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + senderTaxNumber + " not found"))
                .getDivorces();

        BiPredicate<Divorce, Integer> containsName = (d, n) -> {
            List<DivorceStatement> divorceStatements = d.getStatement();
            for (DivorceStatement statement : divorceStatements) {
                if (statement.getPerson().getTaxNumber().equals(n)) {
                    return true;
                }
            }
            return false;
        };

        return divorces.stream().filter(d -> containsName.test(d, taxNumber)).collect(Collectors.toList());
    }

    @GetMapping("/findByName")
//    @PreAuthorize("hasRole('LAWYER') or hasRole('NOTARY') or hasRole('SPOUSE")
    public List<Divorce> findByName(String name, Integer senderTaxNumber) {
        //1. todo security check if taxNumber of auth user ,is the same as the one in the request.taxNumber or is an admin
        List<Divorce> divorces = userRepo.findByTaxNumber(senderTaxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + senderTaxNumber + " not found"))
                .getDivorces();

        BiPredicate<Divorce, String> containsName = (d, n) -> {
            List<DivorceStatement> divorceStatements = d.getStatement();
            for (DivorceStatement statement : divorceStatements) {
                if (statement.getPerson().getLastName().toLowerCase().contains(n.toLowerCase()) ||
                        statement.getPerson().getFirstName().toLowerCase().contains(n.toLowerCase())) {
                    return true;
                }
            }
            return false;
        };

        return divorces.stream().filter(d -> containsName.test(d, name)).collect(Collectors.toList());
    }


    @PostMapping("/edit")
//    @PreAuthorize("hasRole('LAWYER')
    public Divorce edit(@RequestBody DivorceAPI divorceEdits) {
        //1. todo security check if taxNumber of auth user is the same as the one in the lead lawyer

        //3. todo security check divorce status (if it is in the right stage)


        return saveDivorce(divorceEdits, divorceRepo.findById(divorceEdits.getId()).orElseThrow(()->new IllegalArgumentException("Divorce wasn't found in DB")));
    }

    //FIXME Edit divorce without entering all data for related objects (divorceStatement, person, etc)

    @PostMapping("/save")
//    @PreAuthorize("hasRole('LAWYER')
    public Divorce save(@RequestBody Divorce divorce) {
        //1. todo security check if taxNumber of auth user is the same as the one in the lead lawyer

        //3. todo security check divorce status (if it is in the right stage)

        if (divorceRepo.findById(divorce.getId()).orElse(null).isClosed()) {
            if (divorce.getStatus().equals(DivorceStatus.COMPLETED)) {
                throw new IllegalStateException("Divorce is already completed");
            } else {
                throw new IllegalStateException("Divorce is cancelled");
            }
        }

        if (divorce.getStatus().equals(DivorceStatus.COMPLETED) || divorce.getStatus().equals(DivorceStatus.CANCELLED)) {
            throw new IllegalStateException("Divorce status cannot be completed or cancelled by a lawyer");
        }


        if (!divorce.isStatementsValid()) {
            throw new IllegalArgumentException("Divorce statements are not valid. Check for multiple instances of the same person");
        }

        createDivorceStatementsIfNotExist(divorce.getStatement(), divorce);


//        changeAllStatementsToPending(divorce.getStatement());

//        divorceRepo.findById(divorce.getId()).orElse(null).setDate(new Date (System.currentTimeMillis()));

        return divorceRepo.save(divorce);
    }

    @PostMapping("/addStatement")
    public Divorce addStatement(Integer divorceID, @RequestBody DivorceStatement statement) {

        //1. todo security check if taxNumber of auth user is the same as the one in the statement (person)

        //2. todo security check if taxNumber of the User who submits the statement is included in the divorce and the faculty is the same with the role ( lawyers given taxNumbers)
        Divorce divorce = divorceRepo.findById(divorceID).orElseThrow(()
                -> new NoSuchElementException("Divorce with id " + divorceID + " not found"));

        if (!divorce.isClosed()) {
            statement.setTimestamp(new Date(System.currentTimeMillis()));
            divorce.getStatement().add(statement);
            if (statement.getChoice().equals(DivorceStatementChoice.REJECT)) {
                divorce.setStatus(DivorceStatus.CANCELLED);
            } else if (divorce.isAllStatementsAccepted()) {
                divorce.setStatus(DivorceStatus.COMPLETED);
            } else if (divorce.isReadyForNotarialAct()) {
                //todo inform notary
            }
        } else {
            if (divorce.getStatus().equals(DivorceStatus.COMPLETED)) {
                throw new IllegalStateException("Divorce is already completed");
            } else {
                throw new IllegalStateException("Divorce is cancelled");
            }
        }
        return divorce;
    }

    private void changeAllStatementsToPending(List<DivorceStatement> statements) {
        for (DivorceStatement statement : statements) {
            DivorceStatement s = divorceStatementRepo.findById(statement.getId()).orElseThrow(()
                    -> new NoSuchElementException("DivorceStatement with id " + statement.getId() + " not found"));
            s.setChoice(DivorceStatementChoice.PENDING);
            s.setTimestamp(null);
            s.setComment(null);
        }
    }

    private void createDivorceStatementsIfNotExist(List<DivorceStatement> statements, Divorce divorce) {
        List<DivorceStatement> newStatements = new ArrayList<>();
        for (DivorceStatement statement : statements) {
            if (statement.getId() == null) {
                newStatements.add(divorceStatementRepo.save(statement));
            }
//            if (divorceStatementRepo.findById(statement.getId())
//                    .orElseThrow(() -> new NoSuchElementException("Divorce statement with id: " + statement.getId() + " wasn't found"))
//                    .equals(statement)) {
//                newStatements.add(statement);
//            } else {
//               throw new IllegalArgumentException("Divorce statement with id: " + statement.getId() + " already exists and doesn't match the one provided");
//            }

            newStatements.add(divorceStatementRepo.save(statement));
        }
        divorce.setStatement(newStatements);
    }

    /**
     * Prepare and save divorce object to DB
     *
     * @param divorceSource object with source data
     * @param divorce       object subject to changes, give NULL if new Divorce
     */
    private Divorce saveDivorce(DivorceAPI divorceSource, @Nullable Divorce divorce) {
        Divorce finalDivorce;

        if (divorce == null) {
            finalDivorce = divorceRepo.save(new Divorce());
        } else {
            finalDivorce=divorceRepo.findById(divorceSource.getId())
                    .orElseThrow(()-> new NoSuchElementException("Given divorce wasn't found in DB"));
            //Check if it's closed
            if (finalDivorce.isClosed()) {
                if (divorce.getStatus().equals(DivorceStatus.COMPLETED)) {
                    throw new IllegalStateException("Divorce is already completed. Changes are not allowed");
                } else {
                    throw new IllegalStateException("Divorce is cancelled. Changes are not allowed");
                }
            }
        }

        if (divorce == null || divorceSource.isDraft()) {
            finalDivorce.setLeadLawyer(checkRole(divorceSource.getLawyerLeadTaxNumber(), Faculty.LAWYER));

            List<DivorceStatement> finalStatements = new ArrayList<>();

            finalStatements.add(divorceStatementRepo.save(new DivorceStatement(
                    checkRole(divorceSource.getLawyerLeadTaxNumber(), Faculty.LAWYER),
                    Faculty.LAWYER, DivorceStatementChoice.PENDING, finalDivorce)));

            finalStatements.add(divorceStatementRepo.save(new DivorceStatement(
                    checkRole(divorceSource.getLawyerLeadTaxNumber(), Faculty.SPOUSE),
                    Faculty.SPOUSE, DivorceStatementChoice.PENDING, finalDivorce)));

            finalStatements.add(divorceStatementRepo.save(new DivorceStatement(
                    checkRole(divorceSource.getLawyerLeadTaxNumber(), Faculty.SPOUSE),
                    Faculty.SPOUSE, DivorceStatementChoice.PENDING, finalDivorce)));

            finalStatements.add(divorceStatementRepo.save(new DivorceStatement(
                    checkRole(divorceSource.getNotaryTaxNumber(), Faculty.NOTARY),
                    Faculty.NOTARY, DivorceStatementChoice.INACTIVE, finalDivorce)));

            finalDivorce.setStatement(finalStatements);
        }

        finalDivorce.setContractDetails(divorceSource.getContractDetails());

        if (divorceSource.isDraft()) {
            if (divorce!=null && !divorce.getStatus().equals(DivorceStatus.DRAFT)){
                throw new IllegalStateException("Divorce is in pending mode and cannot be changed to draft");
            }
            divorce.setStatus(DivorceStatus.DRAFT);
        } else {
            divorce.setStatus(DivorceStatus.PENDING);
            if(divorce==null){
                finalDivorce.setApplicationDate(new Date(System.currentTimeMillis()));
            }
            //TODO Inform involved parties in order to review the divorce
        }
        return divorceRepo.save(finalDivorce);
    }

    /**
     * Checks role for given tax number
     *
     * @param taxNumber to search on DB
     * @param faculty   to cross-check
     * @return User instance from DB
     * @throws IllegalArgumentException when tax number does not exist in DB or when role isn't assigned
     */
    private User checkRole(Integer taxNumber, Faculty faculty) throws IllegalArgumentException {

        User user = userRepo.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new IllegalArgumentException("User with role: " + faculty.name() + " with tax number: " + taxNumber + " not found."));

        if (!user.getRoles().contains(faculty.name())) {
            throw new IllegalArgumentException("User with tax number: " + taxNumber + " does not have the role: " + faculty.name());
        }

        return user;
    }


}
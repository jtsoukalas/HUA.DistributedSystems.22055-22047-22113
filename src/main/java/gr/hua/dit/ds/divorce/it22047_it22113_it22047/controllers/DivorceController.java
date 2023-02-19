package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceStatusException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.FewerDivorceStatementsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceStatementRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data.DivorceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static javax.swing.UIManager.get;

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

    @Autowired
    DivorceService divorceService;

    /**
     * Returns divorces of the user with the given tax number
     *
     * @param taxNumber we might want to remove the param after security is implemented
     * @param faculty  the faculty of the user in order to filter the divorces
     * @return List of divorces
     */
    @GetMapping("/myDivorces")
//    @PreAuthorize("hasRole('LAWYER') or hasRole('NOTARY') or hasRole('SPOUSE')")
    public List<DivorceAPIResponseConcise> myDivorces(Integer taxNumber, Faculty faculty) throws UserNotFoundException {
        // TODO Security check if the user is allowed to see the divorces
        if (taxNumber == null || faculty == null) {
            throw new IllegalArgumentException("TaxNumber and faculty must be provided");
        }
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new UserNotFoundException(taxNumber))
                .getDivorces().stream().filter(d -> {
                    try {
                        return d.getUser(faculty).getTaxNumber().equals(taxNumber);
                    } catch (UserWithWrongRoleException e) {
                        return false;
                    }
                }).map(d -> new DivorceAPIResponseConcise(d)).collect(Collectors.toList());
    }

    /**
     * Returns divorce details for the given id
     *
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
     *
     * @param query     divorce query
     * @param taxNumber we might want to remove the param after security is implemented
     * @return
     */
    @GetMapping("/search")
    public List<DivorceAPIResponseConcise> search(String query, Integer taxNumber) {
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces().stream().filter(d -> d.search(query))
                .map(d -> new DivorceAPIResponseConcise(d)).collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    public List<Divorce> findAll() {
        return divorceRepo.findAll();
    }

    /**
     * Deletes the divorce with the given id if status is not COMPLETED
     * @param id
     */
    @DeleteMapping("/delete")
    //@PreAuthorize("hasRole('LAWYER')")
    public void delete(Integer id) {
        //TODO Security check if the user is allowed to delete the divorce
        //TODO Implement
        divorceRepo.deleteById(id);
    }

    @PostMapping("/emailInvolvedPartis")
    //@PreAuthorize("hasRole('LAWYER')")
    public void emailInvolvedPartis(Integer id) {
        //TODO Security check if the user is allowed to delete the divorce
        //TODO Implement
    }

    @PostMapping("/notarialAccept")
    //@PreAuthorize("hasRole('NOTARY')")
    public void notarialAccept(Integer id, String notarialDeedNumber) {
        //TODO Security check if the user is allowed to delete the divorce
        //TODO Implement
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
    public DivorceAPIResponse edit(@RequestBody DivorceAPIRequest divorceEdits) throws UserNotFoundException, UserWithWrongRoleException, FewerDivorceStatementsException, DivorceStatusException {
        //1. todo security check if taxNumber of auth user is the same as the one in the lead lawyer

        //3. todo security check divorce status (if it is in the right stage)

        divorceEdits.completenessCheck();
        return new DivorceAPIResponse(divorceService.editDivorce(divorceEdits));
    }

    //FIXME Edit divorce without entering all data for related objects (divorceStatement, person, etc)


    @PostMapping("/save")
//    @PreAuthorize("hasRole('LAWYER')
    public DivorceAPIResponse save(@RequestBody DivorceAPIRequest divorce) throws FewerDivorceStatementsException, DivorceStatusException, UserNotFoundException, UserWithWrongRoleException {
        //1. todo security check if taxNumber of auth user is the same as the one in the lead lawyer
        return new DivorceAPIResponse(divorceService.createDivorce(divorce));
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
}
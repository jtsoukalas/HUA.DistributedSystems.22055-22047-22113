package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceStatusException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.FewerDivorceStatementsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.SimilarDivorceExistsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceStatementRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data.DivorceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static javax.swing.UIManager.get;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/divorce")
@PreAuthorize("hasAuthority('LAWYER') or hasAuthority('NOTARY') or hasAuthority('SPOUSE')")
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
     * @param role of the user in order to filter the divorces
     * @return List of divorces
     */
    @GetMapping("/myDivorces")
    public List<DivorceAPIResponseConcise> myDivorces(Role role) throws UserNotFoundException, UserWithWrongRoleException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        if (taxNumber == null || role == null) {
            throw new IllegalArgumentException("TaxNumber and faculty must be provided");
        }
        User user = userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new UserNotFoundException(taxNumber));
        if (user.hasRole(role)) {
            return user.getDivorces().stream().filter(d -> {
                if (d.getStatus().equals(DivorceStatus.DRAFT)) {
                    if (d.getLawyerLead().getTaxNumber().equals(taxNumber) && role.equals(Role.LAWYER)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }).filter(d -> {
                if (role.equals(Role.LAWYER)) {
                    return d.getLawyerLead().getTaxNumber().equals(taxNumber);
                }
                try {
                    return d.getUserFromStatements(role).getTaxNumber().equals(taxNumber);
                } catch (UserWithWrongRoleException e) {
                    return false;
                }
            }).map(d -> new DivorceAPIResponseConcise(d)).collect(Collectors.toList());
        } else {
            throw new UserWithWrongRoleException(taxNumber, role);
        }
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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        //Check access to divorce
        Divorce divorce = divorceRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Divorce with id " + id + " not found"));
        if (divorce.hasAccess(taxNumber)) {
            return new DivorceAPIResponse(divorce);
        } else {
            throw new NoSuchElementException("Divorce with id " + id + " not found or taxNumber " + taxNumber + " has no access to it");
        }
    }

    /**
     * Returns divorces that mach the given criteria
     *
     * @param query divorce query
     * @return
     */
    @GetMapping("/search")
    public List<DivorceAPIResponseConcise> search(String query) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces().stream().filter(d -> d.search(query) && d.hasAccess(taxNumber))
                .map(d -> new DivorceAPIResponseConcise(d)).collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<DivorceAPIResponseConcise> findAll() {
        List<DivorceAPIResponseConcise> response = new ArrayList<>();
        divorceRepo.findAll().forEach(d -> response.add(new DivorceAPIResponseConcise(d)));
        return response;
    }

    /**
     * Edit divorce
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('LAWYER')")
    public DivorceAPIResponse edit(@RequestBody DivorceAPIRequest divorceEdits) throws
            UserNotFoundException, UserWithWrongRoleException, FewerDivorceStatementsException, DivorceStatusException, SimilarDivorceExistsException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        if (!divorceRepo.findById(divorceEdits.getId()).orElseThrow(() -> new DivorceNotFoundException(divorceEdits))
                .getLawyerLead().getTaxNumber().equals(taxNumber)) {
            throw new NoSuchElementException("Divorce with id " + divorceEdits.getId() + " not found or taxNumber " + taxNumber + " has no access to it");
        }
        divorceEdits.completenessCheck();
        return new DivorceAPIResponse(divorceService.edit(divorceEdits));
    }

    /**
     * Create divorce
     *
     * @param divorce
     * @return
     * @throws FewerDivorceStatementsException
     * @throws DivorceStatusException
     * @throws UserNotFoundException
     * @throws UserWithWrongRoleException
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('LAWYER')")
    public DivorceAPIResponse save(@RequestBody DivorceAPIRequest divorce) throws
            FewerDivorceStatementsException, DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, SimilarDivorceExistsException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        if(!divorce.getLawyerLeadTaxNumber().equals(taxNumber)){
            throw new IllegalArgumentException("Tax number of the lawyer lead must be the same as the tax number of the logged in user");
        }
        return new DivorceAPIResponse(divorceService.create(divorce));
    }

    /**
     * Deletes the divorce with the given id if status is not COMPLETED
     *
     * @param id
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('LAWYER')")
    public void delete(Integer id) {
        //TODO Security check if the user is allowed to delete the divorce
        //TODO Implement
        divorceRepo.deleteById(id);
    }

    @PostMapping("/emailInvolvedPartis")
    @PreAuthorize("hasAuthority('LAWYER')")
    public void emailInvolvedPartis(Integer id) {
        //TODO Security check if the user is allowed to delete the divorce
        //TODO Implement
    }

    @PostMapping("/notarialAccept")
    @PreAuthorize("hasAuthority('NOTARY')")
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
            List<DivorceStatement> divorceStatements = d.getStatements();
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
            List<DivorceStatement> divorceStatements = d.getStatements();
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

    @PostMapping("/addStatement")
    public Divorce addStatement(Integer divorceID, @RequestBody DivorceStatement statement) {

        //1. todo security check if taxNumber of auth user is the same as the one in the statement (person)

        //2. todo security check if taxNumber of the User who submits the statement is included in the divorce and the faculty is the same with the role ( lawyers given taxNumbers)
        Divorce divorce = divorceRepo.findById(divorceID).orElseThrow(()
                -> new NoSuchElementException("Divorce with id " + divorceID + " not found"));

        if (!divorce.isClosed()) {
            statement.setTimestamp(new Date(System.currentTimeMillis()));
            divorce.getStatements().add(statement);
            if (statement.getChoice().equals(DivorceStatementChoice.REJECTED)) {
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
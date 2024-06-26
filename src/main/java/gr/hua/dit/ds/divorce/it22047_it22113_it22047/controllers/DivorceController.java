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
import org.apache.naming.factory.SendMailFactory;
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

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:3000")
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
//                if (role.equals(Role.LAWYER)) {
//                    return d.getLawyerLead().getTaxNumber().equals(taxNumber);
//                }
                try {
                    List<User> users = d.getUserFromStatements(role);
                    for (User u : users) {
                        if (u.getTaxNumber().equals(taxNumber)) {
                            return true;
                        }
                    }
                    return false;
                } catch (UserWithWrongRoleException e) {
                    return false;
                }
            }).map(d -> new DivorceAPIResponseConcise(d, taxNumber)).collect(Collectors.toList());
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
        User user = userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"));
        if (user.hasRole(Role.ADMIN) || divorce.hasAccess(taxNumber)) {
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
                .map(d -> new DivorceAPIResponseConcise(d,taxNumber)).collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<DivorceAPIResponseConcise> findAll() {
        List<DivorceAPIResponseConcise> response = new ArrayList<>();
        divorceRepo.findAll().forEach(d -> response.add(new DivorceAPIResponseConcise(d,null)));
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
        divorce.setLawyerLeadTaxNumber(taxNumber);
        return new DivorceAPIResponse(divorceService.create(divorce));
    }

    /**
     * Deletes the divorce with the given id if status is not COMPLETED
     *
     * @param id
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('LAWYER')")
    public void delete(Integer id) throws DivorceStatusException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        Divorce divorce = divorceRepo.findById(id).orElseThrow(() -> new DivorceNotFoundException(id));
        if (!divorce.getLawyerLead().getTaxNumber().equals(taxNumber)) {
            throw new DivorceNotFoundException(id);
        }
        divorceService.delete(divorce);
    }

    @PostMapping("/notarialAccept")
    @PreAuthorize("hasAuthority('NOTARY')")
    public void notarialAccept(Integer id, String notarialDeedNumber) {
        //TODO Security check if the user is allowed to delete the divorce
        //TODO Implement
    }

    @PostMapping("/addStatement")
    public void addStatement(@RequestBody DivorceStatementAPIRequest statementAPI) throws UserNotFoundException, UserWithWrongRoleException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());

        divorceService.addStatement(statementAPI, taxNumber);
    }

    @GetMapping("/remindParties")
    @PreAuthorize("hasAuthority('LAWYER')")
    public void remindParties(Integer divorceId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        Divorce divorce = divorceRepo.findById(divorceId).orElseThrow(() -> new DivorceNotFoundException(divorceId));
        if (!divorce.getLawyerLead().getTaxNumber().equals(taxNumber)) {
            throw new DivorceNotFoundException(divorceId);
        }
        divorceService.remindParties(divorce);
    }
}
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
     * @param role of the user in order to filter the divorces
     * @return List of divorces
     */
    @GetMapping("/myDivorces")
    public List<DivorceAPIResponseConcise> myDivorces(Integer taxNumber, Role role) throws UserNotFoundException, UserWithWrongRoleException {
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
    public DivorceAPIResponse findById(Integer taxNumber,Integer id) throws NoSuchElementException {
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
    public List<DivorceAPIResponseConcise> search(Integer taxNumber,String query) {
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces().stream().filter(d -> d.search(query) && d.hasAccess(taxNumber))
                .map(d -> new DivorceAPIResponseConcise(d)).collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    public List<DivorceAPIResponseConcise> findAll() {
        List<DivorceAPIResponseConcise> response = new ArrayList<>();
        divorceRepo.findAll().forEach(d -> response.add(new DivorceAPIResponseConcise(d)));
        return response;
    }

    /**
     * Edit divorce
     */
    @PostMapping("/edit")
    public DivorceAPIResponse edit(Integer taxNumber,@RequestBody DivorceAPIRequest divorceEdits) throws
            UserNotFoundException, UserWithWrongRoleException, FewerDivorceStatementsException, DivorceStatusException, SimilarDivorceExistsException {
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
    public DivorceAPIResponse save(Integer taxNumber,@RequestBody DivorceAPIRequest divorce) throws
            FewerDivorceStatementsException, DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, SimilarDivorceExistsException {
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
    public void delete(Integer taxNumber,Integer id) throws DivorceStatusException {
        Divorce divorce = divorceRepo.findById(id).orElseThrow(() -> new DivorceNotFoundException(id));
        if (!divorce.getLawyerLead().getTaxNumber().equals(taxNumber)) {
            throw new DivorceNotFoundException(id);
        }
        divorceService.delete(divorce);
    }

    @PostMapping("/addStatement")
    public void addStatement(Integer taxNumber,@RequestBody DivorceStatementAPIRequest statementAPI) throws UserNotFoundException, UserWithWrongRoleException {
        divorceService.addStatement(statementAPI, taxNumber);
    }

    @GetMapping("/remindParties")
    public void remindParties(Integer taxNumber,Integer divorceId){
        Divorce divorce = divorceRepo.findById(divorceId).orElseThrow(()-> new DivorceNotFoundException(divorceId));
        if (!divorce.getLawyerLead().getTaxNumber().equals(taxNumber)){
            throw new DivorceNotFoundException(divorceId);
        }
        divorceService.remindParties(divorce);
    }
}
package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceStatementRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/divorce")
public class DivorceController{

    @Autowired
    DivorceRepository divorceRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    DivorceStatementRepository divorceStatementRepo;

    @Autowired
    DivorceDAO divorceDAO;

    @GetMapping("/findAll")
    public List<Divorce> findAll(){
        return divorceRepo.findAll();
    }

    @GetMapping("/adminFind")
//        @PreAuthorize("hasRole('ADMIN')")
    public List<Divorce> findByTaxNumber(Integer taxNumber){
//        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new UsernameNotFoundException("User with tax number " + taxNumber + " not found"))
//                .getCases();  //fixme security
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces();
    }

    @GetMapping("/findByTaxNumber")
//    @PreAuthorize("hasRole('LAWYER') or hasRole('NOTARY') or hasRole('SPOUSE")
    public List<Divorce> findByTaxNumber(Integer senderTaxNumber, Integer taxNumber){
        //1. todo check if taxNumber of auth user ,is the same as the one in the request.taxNumber or is an admin
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces();
    }

    @GetMapping("/findByName")
//    @PreAuthorize("hasRole('LAWYER') or hasRole('NOTARY') or hasRole('SPOUSE")
    public List<Divorce> findByName(String name, Integer senderTaxNumber){
        //1. todo check if taxNumber of auth user ,is the same as the one in the request.taxNumber or is an admin
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

        return divorces.stream().filter(d-> containsName.test(d,name)).collect(Collectors.toList());
    }


    @PostMapping("/save")
//    @PreAuthorize("hasRole('LAWYER')
    public Divorce save(@RequestBody Divorce divorce){
        //1. todo check if taxNumber of auth user is the same as the one in the lead lawyer

        //3. todo check divorce status (if it is in the right stage)

        if(divorceRepo.findById(divorce.getId()).orElse(null).isClosed()){
            if (divorce.getStatus().equals(DivorceStatus.COMPLETED)) {
                throw new IllegalStateException("Divorce is already completed");
            } else {
                throw new IllegalStateException("Divorce is cancelled");
            }
        }

        if (divorce.getStatus().equals(DivorceStatus.COMPLETED) || divorce.getStatus().equals(DivorceStatus.CANCELLED)) {
            throw new IllegalStateException("Divorce cannot be completed or cancelled by a lawyer");
        }

        //fixme 
        divorce.changeAllStatementsToPending();

        divorceRepo.findById(divorce.getId()).orElse(null).setApplicationDate(new Date (System.currentTimeMillis()));

        return divorceRepo.save(divorce);
    }

    @PostMapping("/addStatement")
    public Divorce addStatement(Integer divorceID, @RequestBody DivorceStatement statement){

        //1. todo check if taxNumber of auth user is the same as the one in the statement (person)

        //2. todo check if taxNumber of the User who submits the statement is included in the divorce and the faculty is the same with the role ( lawyers given taxNumbers)
        Divorce divorce = divorceRepo.findById(divorceID).orElseThrow(()
                -> new NoSuchElementException("Divorce with id " + divorceID + " not found"));

        //3. todo check divorce status (if it is in the right stage)
        if (!divorce.isClosed()) {
            statement.setTimestamp(new Date(System.currentTimeMillis()));
            divorce.getStatement().add(statement);
            if(statement.getChoice().equals(DivorceStatementStatus.REJECT)){
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
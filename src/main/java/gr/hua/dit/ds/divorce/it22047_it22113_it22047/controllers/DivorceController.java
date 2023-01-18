package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceStatementRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/find/{taxNumber}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    //1. todo check if taxNumber of auth user ,is the same as the one in the request.taxNumber or is an admin

    public List<Divorce> findByTaxNumber(@PathVariable Integer taxNumber){
//        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new UsernameNotFoundException("User with tax number " + taxNumber + " not found"))
//                .getCases();  //fixme security
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getDivorces();
    }

    @PostMapping("/save")
    public Divorce save(@RequestBody Divorce divorce){
        //1. todo check if taxNumber of auth user is the same as the one in the lead lawyer

        //3. todo check divorce status (if it is in the right stage)
        return divorceRepo.save(divorce);

    }

    @PostMapping("/{divorceID}/statements/add/")
    public Divorce addStatement(@PathVariable Integer divorceID, @RequestBody Statement statement){
        //1. todo check if taxNumber of auth user is the same as the one in the statement (person)

        //2. todo check if taxNumber of the User who submits the statement is included in the divorce and the faculty is the same with the role ( lawyers given taxNumbers)

        //3. todo check divorce status (if it is in the right stage)


        return null;
    }

    @GetMapping("/findall")
    //Only admin can see all users
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Divorce> findAll(){
        return divorceRepo.findAll();
    }


}
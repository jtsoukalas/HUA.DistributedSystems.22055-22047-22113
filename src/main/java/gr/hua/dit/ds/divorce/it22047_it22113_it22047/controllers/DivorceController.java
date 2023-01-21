package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatement;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
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

    @GetMapping("/find")
    public List<Divorce> findByTaxNumber(Integer taxNumber){
//        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new UsernameNotFoundException("User with tax number " + taxNumber + " not found"))
//                .getCases();  //fixme security
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new NoSuchElementException("User with tax number " + taxNumber + " not found"))
                .getCases();
    }

    @PostMapping("/save/")
    public Divorce save(@RequestBody Divorce divorce){
        return divorceRepo.save(divorce);
    }

    @PostMapping("/statements/add/{divorceID}")
    public Divorce addStatement(@PathVariable Integer divorceID, @RequestBody DivorceStatement statement){

        //find divorce
        Divorce divorce = divorceRepo.findById(divorceID).orElseThrow(() -> new NoSuchElementException("Divorce with id " + divorceID + " not found"));
        //add statement to divorce
//        divorce.getStatement().add(statement);
        divorceStatementRepo.save(statement);
//        divorceRepo.save(divorce);

        return divorce;
    }

    @GetMapping("/findall")
    public List<Divorce> findAll(){
        return divorceRepo.findAll();
    }


}
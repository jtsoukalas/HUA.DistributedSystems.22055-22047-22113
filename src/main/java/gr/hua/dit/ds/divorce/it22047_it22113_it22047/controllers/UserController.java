package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private DivorceDAO divorceDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/find/{taxNumber}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    //1. todo check if taxNumber of auth user is the same as the one in the statement (person)

    //2. todo check if taxNumber of the User who submits the statement is included in the divorce and the faculty is the same with the role ( lawyers given taxNumbers)

    //3. todo check divorce status (if it is in the right stage)
    public User findByTaxNumber(@PathVariable Integer taxNumber){
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping("/invite/{taxNumber}/{email}")
    public Divorce invite(@PathVariable String taxNumber, @PathVariable String email){
        //        fixme
        //        Code here
        return null;
    }

    @PostMapping("/save")
//    @PreAuthorize("hasRole('LAWYER')")
    public User save(@RequestBody User user){
        // 1. todo check if taxNumber of auth user is the same as the lead lawyer of the divorce

        //2. todo check divorce status (if it is in the right stage)
        return userRepo.save(user);
    }

    @GetMapping("/findall")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findAll(){
        return userRepo.findAll();
//                .orElseThrow(NoSuchElementException::new);
    }


    @DeleteMapping("/delete/{taxNumber}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer taxNumber){
        userRepo.delete(userRepo.findByTaxNumber(taxNumber).orElseThrow(NoSuchElementException::new));
    }
}

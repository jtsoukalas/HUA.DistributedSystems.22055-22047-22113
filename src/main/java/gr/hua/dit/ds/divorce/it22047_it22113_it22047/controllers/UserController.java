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
import java.util.function.Supplier;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private DivorceDAO divorceDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/find")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User findByTaxNumber(Integer taxNumber) {
        User user = userRepo.findByTaxNumber(taxNumber).orElseThrow(()-> new NoSuchElementException("There is no user with tax number " + taxNumber));
         return user; //fixme
    }


    @GetMapping("/findall")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @PostMapping("/invite/{taxNumber}/{email}")
    public Divorce invite(@PathVariable String taxNumber, @PathVariable String email) {
        //        fixme
        //        Code here
        return null;
    }

    @PostMapping("/save")
//    @PreAuthorize("hasRole('LAWYER')")
    public User save(@RequestBody User user) {
        // 1. todo check if taxNumber of auth user is the same as the lead lawyer of the divorce

        //2. todo check divorce status (if it is in the right stage)

        // todo user cannot change taxNumber, identityNumber, fnale, lname
        return userRepo.save(user);
    }


    @PostMapping("/disableAccess")
    //    @PreAuthorize("hasRole('ADMIN')")
    public void disableAccessByTaxNumber(Integer taxNumber) {
        User user = userRepo.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new NoSuchElementException("User with taxNumber " + taxNumber + " not found"));
        user.setEnabled(false);
        userRepo.save(user);
    }
}

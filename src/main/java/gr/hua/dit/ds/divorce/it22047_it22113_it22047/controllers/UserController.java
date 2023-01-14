package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController{
    @Autowired
    private DivorceDAO divorceDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/find/{taxNumber}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User findByTaxNumber(@PathVariable Integer taxNumber){
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping("/invite/{taxNumber}/{email}")
    public Divorce invite(@PathVariable String taxNumber, @PathVariable String email){
        //        fixme
        //        Code here
        return null;
    }
}

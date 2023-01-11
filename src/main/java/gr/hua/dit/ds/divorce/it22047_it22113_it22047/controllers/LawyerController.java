package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lawyers")
public class LawyerController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DivorceDAO divorceDAO;

    @GetMapping("/{taxNumber}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('ADMIN')")
    User findByTaxNumber(@PathVariable String taxNumber) {
        return userDAO.findById(taxNumber);
    }



//    @PostMapping("")
//    @PreAuthorize("hasRole('LAWYER')")
//    Divorce createDivorce(){
//        return divorceDAO.save(Divorce divorce);
//    }



    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    User save(@RequestBody User user) {
        user.setTaxNumber(0);
        userDAO.save(user);
        return user;
    }

    @GetMapping("/{id}")
    User get(@PathVariable int id) {
        User user = userDAO.findById(id);
        return user;
    }

}
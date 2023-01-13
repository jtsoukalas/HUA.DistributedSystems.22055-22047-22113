package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController{
    @Autowired
    private DivorceDAO divorceDAO;

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/find/{taxNumber}")
    public User findByTaxNumber(@PathVariable String taxNumber){
        //        fixme
        //        Code here
        return null;
    }

    @PostMapping("/invite/{taxNumber}/{email}")
    public Divorce invite(@PathVariable String taxNumber, @PathVariable String email){
        //        fixme
        //        Code here
        return null;
    }
}

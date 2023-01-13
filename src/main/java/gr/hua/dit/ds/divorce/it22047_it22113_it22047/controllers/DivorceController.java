package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/divorce")
public class DivorceController{

    @Autowired
    private DivorceDAO divorceDAO;

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/find/{taxNumber}")
    public List<Divorce> findByTaxNumber(@PathVariable String taxNumber){
        //        fixme
        //        Code here
        return null;
    }

    @PostMapping("/save/{divorce}")
    public Divorce save(@PathVariable Divorce divorce){
        //        fixme
        //        Code here
        return null;
    }

    @PostMapping("/statement/add/{divorceID}/{statment}")
    public Divorce save(@PathVariable Divorce divorce, @PathVariable Statement statement){
        //        fixme
        //        Code here
        return null;
    }


}
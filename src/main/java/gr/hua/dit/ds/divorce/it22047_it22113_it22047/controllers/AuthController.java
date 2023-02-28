package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.UserAPI;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepo;

    @PostMapping("/login")
    public boolean login(Integer taxNumber, String password) throws UserNotFoundException {
        return userRepo.findByTaxNumber(taxNumber).orElseThrow(()-> new UserNotFoundException(taxNumber)).getPassword().equalsIgnoreCase(password);
    }

    @PostMapping("/register")
    public User register (@RequestBody UserAPI userAPI) {
        return userRepo.save(userAPI.toUser());
    }
}

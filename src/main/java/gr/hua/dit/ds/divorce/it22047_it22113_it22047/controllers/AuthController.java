package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.payload.request.LoginRequest;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.payload.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authentication")
public class AuthController {
    @Autowired
    private UserDAO userDAO;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        //        fixme
        //        Code here
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignUpRequest signUpRequest){
        //        fixme
        //        Code here
        return null;
    }
}
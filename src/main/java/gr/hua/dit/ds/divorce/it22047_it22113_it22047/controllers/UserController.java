package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Role;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.UserStatus;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.UserAPI;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

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


    @PostMapping("/edit")
    public void edit (@RequestBody UserAPI userAPI) throws UserNotFoundException {
        //Todo security check if user is admin or user himself
        Logger.getLogger("UserController").info(userAPI.toString());
        User user = userRepo.findByTaxNumber(userAPI.getTaxNumber()).orElseThrow(()-> new UserNotFoundException(userAPI.getTaxNumber()));
        user.update(userAPI);
        userRepo.save(user);
    }

    @GetMapping("/find")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public UserAPI findByTaxNumber(Integer taxNumber) throws UserNotFoundException {
        //TODO security check if user has access
        User user = userRepo.findByTaxNumber(taxNumber).orElseThrow(() -> new UserNotFoundException(taxNumber));
        return new UserAPI(user);
    }

    @GetMapping("/findall")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    //    @PreAuthorize("hasRole('ADMIN')")
    public void disableAccessByTaxNumber(Integer taxNumber) {
        User user = userRepo.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new NoSuchElementException("User with taxNumber " + taxNumber + " not found"));
        user.setEnabled(false);
        userRepo.save(user);
    }

    @Autowired
    private EmailSenderService senderService;

    @PostMapping("/invite")
    @PreAuthorize("hasAuthority('LAWYER')")
    public String invite(Integer taxNumber, String email) {
        User user = userRepo.findByTaxNumber(taxNumber).orElse(null);

        if (user != null && user.getEmail().toLowerCase().equals(email.toLowerCase())) {
            switch (user.getUserStatus()){
                case ENABLED:
                    throw new IllegalArgumentException("User is already enabled");
                case DISABLED:
                    throw new IllegalArgumentException("User is disabled. Please contact an admin");
                case PENDING_APPROVAL:
                    throw new IllegalArgumentException("User is pending approval from admin");
            }
        } else if (user != null) {
            throw new IllegalArgumentException("User already exists with given tax number and different email");
        }

        User newUser = new User();
        newUser.setTaxNumber(taxNumber);
        newUser.setEmail(email);
        newUser.setEnabled(false);
        newUser.setRole(Role.SPOUSE);
        newUser.setUserStatus(UserStatus.PENDING_REGISTRATION);

        userRepo.save(newUser);

        senderService.sendSimpleEmail(email,
                "Invitation to divorce application",
                "Message to person with tax number: "+ taxNumber+" \nYou have been invited to a divorce application. Please register at http://localhost:3000/register");
        return "Invitation sent";
    }

    @GetMapping("")
    public UserAPI profile() throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Integer taxNumber = Integer.valueOf(userDetails.getUsername());
        return new UserAPI(userRepo.findByTaxNumber(taxNumber).orElseThrow(()-> new UserNotFoundException(taxNumber)));
    }
}


package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.UserDAO;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatus;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.UserStatus;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.FetchType;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/statistics")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class Statistics {
    @Autowired
    private DivorceRepository divorceRepo;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/divorces/completed")
    public long completedDivorces() {
       return divorceRepo.findAll().stream().filter(divorce -> divorce.getStatus().equals(DivorceStatus.COMPLETED)).count();
    }

    @GetMapping("/divorces/pending")
    public long pendingDivorces() {
       return divorceRepo.findAll().stream().filter(divorce -> divorce.getStatus().equals(DivorceStatus.PENDING)).count();
    }

    @GetMapping("/divorces/canceled")
    public long cancelledDivorces() {
       return divorceRepo.findAll().stream().filter(divorce -> divorce.getStatus().equals(DivorceStatus.CANCELLED)).count();
    }

    @GetMapping("/divorces/draft")
    public long rejectedDivorces() {
       return divorceRepo.findAll().stream().filter(divorce -> divorce.getStatus().equals(DivorceStatus.DRAFT)).count();
    }

    @GetMapping("/divorces")
    public long totalDivorces() {
       return divorceRepo.findAll().stream().count();
    }

    @GetMapping("/users")
    public long totalUsers() {
       return userRepo.findAll().stream().count();
    }

    @GetMapping("/users/active")
    public long activeUsers() {
       return userRepo.findAll().stream().filter(user -> user.getUserStatus().equals(UserStatus.ENABLED)).count();
    }

    @GetMapping("/users/inactive")
    public long inactiveUsers() {
       return userRepo.findAll().stream().filter(user -> user.getUserStatus().equals(UserStatus.DISABLED)).count();
    }

    @GetMapping("/users/pendingRegistration")
    public long pendingUsers() {
       return userRepo.findAll().stream().filter(user -> user.getUserStatus().equals(UserStatus.PENDING_REGISTRATION)).count();
    }

    @GetMapping("/users/pendingApproval")
    public long pendingAprUsers() {
       return userRepo.findAll().stream().filter(user -> user.getUserStatus().equals(UserStatus.PENDING_APPROVAL)).count();
    }


}

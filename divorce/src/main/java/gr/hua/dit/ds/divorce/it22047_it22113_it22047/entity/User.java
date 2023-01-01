package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="tax_number")
    private String taxNumber;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="identity_card_number")
    private String identityCardNumber;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="roles")
    @ElementCollection
    private List<Role> roles;

    @Column(name="user_status")
    private UserStatus userStatus;

    @Column(name="register_timestamp")
    private Date registerTimestamp;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "divorce_id")
    private List<Divorce> cases;

}

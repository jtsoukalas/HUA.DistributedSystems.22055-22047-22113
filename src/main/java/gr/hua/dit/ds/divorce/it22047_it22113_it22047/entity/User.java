package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @ElementCollection(targetClass=Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="user_roles")
    @Column(name="roles")
    private Collection<Role> interests;

    @Column(name="user_status")
    private UserStatus userStatus;

    @Column(name="register_timestamp")
    private Date registerTimestamp;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "divorce_id")
    private List<Divorce> cases;

    public User(String taxNumber, String firstName, String lastName, String identityCardNumber, String email, String password, String phoneNumber, Collection<Role> interests, UserStatus userStatus, Date registerTimestamp, List<Divorce> cases) {
        this.taxNumber = taxNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityCardNumber = identityCardNumber;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.interests = interests;
        this.userStatus = userStatus;
        this.registerTimestamp = registerTimestamp;
        this.cases = cases;
    }

    public User() {}

    public String getTaxNumber() {return taxNumber;}

    public void setTaxNumber(String taxNumber) {this.taxNumber = taxNumber;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<Role> getInterests() {return interests;}

    public void setInterests(Collection<Role> interests) {this.interests = interests;}

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Date getRegisterTimestamp() {
        return registerTimestamp;
    }

    public void setRegisterTimestamp(Date registerTimestamp) {
        this.registerTimestamp = registerTimestamp;
    }

    public List<Divorce> getCases() {
        return cases;
    }

    public void setCases(List<Divorce> cases) {
        this.cases = cases;
    }
}

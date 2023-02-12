package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="tax_number")
    private Integer taxNumber;

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
    private Collection<Role> roles;

    @Column(name="user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name="register_timestamp")
    private Date registerTimestamp;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_divorce",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "divorce_id"))
    @JsonBackReference
    private List<Divorce> divorces;

    @Column(name="enabled")
    @NotNull
    private boolean enabled = false;

    public User(Integer taxNumber, String firstName, String lastName, String identityCardNumber, String email, String password, String phoneNumber, Collection<Role> interests, UserStatus userStatus, Date registerTimestamp, List<Divorce> divorces) {
        this.taxNumber = taxNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityCardNumber = identityCardNumber;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = interests;
        this.userStatus = userStatus;
        this.registerTimestamp = registerTimestamp;
        this.divorces = divorces;
        this.enabled=false;
    }

    public User() {}

    public Integer getTaxNumber() {return taxNumber;}

    public void setTaxNumber(Integer taxNumber) {this.taxNumber = taxNumber;}

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

    public Collection<Role> getRoles() {return roles;}

    public void setRoles(Collection<Role> roles) {this.roles = roles;}

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

    public List<Divorce> getDivorces() {
        return divorces;
    }

    public void setDivorces(List<Divorce> divorces) {
        this.divorces = divorces;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFullName() {
        if (firstName ==null) {
            return lastName;
        }
        if (lastName ==null) {
            return firstName;
        }
        return this.firstName + " " + this.lastName;
    }
}
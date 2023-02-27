package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.UserAPI;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.security.auth.RegisterRequest;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "user")
public class User implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

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
    private List<Divorce> divorces = new ArrayList<>();

    @Column(name="enabled")
    @NotNull
    private boolean enabled = false;

    public User(Integer taxNumber, String firstName, String lastName, String identityCardNumber, String email, String password, String phoneNumber, Role interests, UserStatus userStatus, Date registerTimestamp, List<Divorce> divorces) {
        this.taxNumber = taxNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityCardNumber = identityCardNumber;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = interests;
        this.userStatus = userStatus;
        this.registerTimestamp = registerTimestamp;
        this.divorces = divorces;
        this.enabled=false;
    }

    public User (RegisterRequest registerRequest){
        this.taxNumber = registerRequest.getTaxNumber();
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();
        this.identityCardNumber = registerRequest.getIdentityCardNumber();
        this.email = registerRequest.getEmail();
        this.password = registerRequest.getPassword();
        this.phoneNumber = registerRequest.getPhoneNumber();
        this.role = registerRequest.getRole();
        this.userStatus = UserStatus.PENDING_APPROVAL;
        this.registerTimestamp = new Date();
        this.divorces = new ArrayList<>();
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

    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.SPOUSE.name()));
        switch (role){
            case LAWYER -> authorities.add(new SimpleGrantedAuthority(Role.LAWYER.name()));
            case NOTARY -> authorities.add(new SimpleGrantedAuthority(Role.NOTARY.name()));
            case ADMIN -> authorities.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
        }
        return authorities;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return taxNumber.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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

    public Role getRole() {
        return role;
      }

    public void setRole(Role role) {this.role = role;}

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

    @Transactional
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

    @Transactional
    public User addDivorce(Divorce divorce) {
        if (this.divorces== null) {
            this.divorces = new ArrayList<>();
        } else if (this.divorces.contains(divorce)) {
            return this;
        }
        this.divorces.add(divorce);
        return this;
    }

    @Transactional
    public User removeDivorce(Divorce divorce) {
        if (this.divorces== null) {
            return this;
        } else if (!this.divorces.contains(divorce)) {
            return this;
        }
        this.divorces.remove(divorce);
        return this;
    }

    public void update(UserAPI userAPI) {
        this.firstName = userAPI.getFirstName();
        this.lastName = userAPI.getLastName();
        this.identityCardNumber = userAPI.getIdentityCardNumber();
        this.email = userAPI.getEmail();
        this.phoneNumber = userAPI.getPhoneNumber();
    }

    @Transactional
    public boolean hasRole(Role role) {
        return getAuthorities().contains(new SimpleGrantedAuthority(role.name()));
    }

}
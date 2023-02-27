package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Role;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.UserStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserAPI {
    private Integer taxNumber;
    private String firstName;
    private String lastName;
    private String identityCardNumber;
    private String email;
    private String phoneNumber;
    private String role;
    private String userStatus;

    public UserAPI(){}

    public UserAPI(User user){
        this.taxNumber = user.getTaxNumber();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.identityCardNumber = user.getIdentityCardNumber();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.role= user.getRole().name();
        this.userStatus = user.getUserStatus().toString();

    }

    public Integer getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Integer taxNumber) {
        this.taxNumber = taxNumber;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}

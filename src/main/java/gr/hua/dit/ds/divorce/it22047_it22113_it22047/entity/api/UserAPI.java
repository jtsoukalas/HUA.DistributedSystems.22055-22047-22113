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
    private List<String> roles;
    private String userStatus;

    public UserAPI(){}

    public UserAPI(User user){
        this.taxNumber = user.getTaxNumber();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.identityCardNumber = user.getIdentityCardNumber();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            this.roles.add(role.toString());
        }
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}

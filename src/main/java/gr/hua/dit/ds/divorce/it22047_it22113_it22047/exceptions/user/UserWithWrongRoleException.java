package gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Faculty;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;

public class UserWithWrongRoleException extends Exception {

    User user;
    Faculty wantedFaculty;
    int taxNumber;

    public UserWithWrongRoleException(int taxNumber, Faculty wantedFaculty) {
        this.taxNumber = taxNumber;
        this.wantedFaculty = wantedFaculty;
    }

    public UserWithWrongRoleException(User user, Faculty wantedFaculty) {
        this.wantedFaculty = wantedFaculty;
        this.user = user;
    }

    public UserWithWrongRoleException(String s) {
    }

    @Override
    public String getMessage() {
        if(user != null) {
            return ("User with full name: " + user.getFullName() + " has not the role: " + wantedFaculty.getRole());
        } else if(taxNumber != 0) {
            return ("User with tax number: " + taxNumber + " has not the role: " + wantedFaculty.getRole());
        } else {
            return ("User with full name: " + user.getFullName() + " and tax number: "+user.getTaxNumber()+" has not the role: " + wantedFaculty.getRole());
        }
    }
}


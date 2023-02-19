package gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.User;

public class UserNotFoundException extends Exception {

    private User user;
    private int taxNumber;


    public UserNotFoundException(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public UserNotFoundException(User user) {
        this.user = user;
    }

    @Override
    public String getMessage() {
        if (user != null) {
            return ("User with full name: " + user.getFullName() + " wasn't found");
        } else if (taxNumber != 0) {
            return ("User with tax number: " + taxNumber + " wasn't found");
        } else {
            return ("User with full name: " + user.getFullName() + " and tax number: "+user.getTaxNumber()+" wasn't found");
        }
    }
}

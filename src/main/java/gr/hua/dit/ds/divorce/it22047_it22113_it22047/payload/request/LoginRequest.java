package gr.hua.dit.ds.divorce.it22047_it22113_it22047.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String taxnumber;

    @NotBlank
    private String password;

    public String getUsername() {
        return taxnumber;
    }

    public void setUsername(String username) {
        this.taxnumber = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

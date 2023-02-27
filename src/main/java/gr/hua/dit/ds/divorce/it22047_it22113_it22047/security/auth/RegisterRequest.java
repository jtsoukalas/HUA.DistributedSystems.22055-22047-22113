package gr.hua.dit.ds.divorce.it22047_it22113_it22047.security.auth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Role;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private Integer taxNumber;
  private String firstName;
  private String lastName;
  private String identityCardNumber;
  private String email;
  private String password;
  private String phoneNumber;
  private Role role;

}

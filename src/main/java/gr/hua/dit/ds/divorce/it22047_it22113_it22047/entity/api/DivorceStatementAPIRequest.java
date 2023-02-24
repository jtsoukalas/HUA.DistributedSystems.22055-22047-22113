package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatement;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatementChoice;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Faculty;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Role;


public class DivorceStatementAPIRequest {
    private Integer divorceID;
    private Integer taxNumber;
    private Role role;
    private DivorceStatementChoice choice;
    private String comment;
}

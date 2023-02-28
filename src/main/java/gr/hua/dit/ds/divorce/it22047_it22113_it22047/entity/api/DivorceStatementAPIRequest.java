package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatement;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatementChoice;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Faculty;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Role;


public class DivorceStatementAPIRequest {
    private Integer divorceID;
    private Role role;
    private DivorceStatementChoice choice;
    private String comment;

    public DivorceStatementAPIRequest() {
    }

    public DivorceStatementAPIRequest(DivorceStatement statement) {
        this.divorceID = statement.getId();
        this.role = statement.getPerson().getRole();
        this.choice = statement.getChoice();
        this.comment = statement.getComment();
    }

    public Integer getDivorceID() {
        return divorceID;
    }

    public void setDivorceID(Integer divorceID) {
        this.divorceID = divorceID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public DivorceStatementChoice getChoice() {
        return choice;
    }

    public void setChoice(DivorceStatementChoice choice) {
        this.choice = choice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

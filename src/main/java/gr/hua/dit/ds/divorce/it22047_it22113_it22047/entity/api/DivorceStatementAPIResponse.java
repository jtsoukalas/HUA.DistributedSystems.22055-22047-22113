package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatement;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatementChoice;

import java.util.Date;
import java.util.List;

public class DivorceStatementAPIResponse {
    private String fullName;
    private String faculty;
    private DivorceStatementChoice choice;
    private Date timestamp;
    private String comment;

    public DivorceStatementAPIResponse(DivorceStatement statement) {
        this.fullName = statement.getPerson().getFullName();
        this.faculty = statement.getFaculty().toString();
        this.choice = statement.getChoice();
        this.timestamp = statement.getTimestamp();
        this.comment = statement.getComment();
    }
    public static List<DivorceStatementAPIResponse> fromList(List<DivorceStatement> statements) {
        return statements.stream().map(DivorceStatementAPIResponse::new).toList();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public DivorceStatementChoice getChoice() {
        return choice;
    }

    public void setChoice(DivorceStatementChoice choice) {
        this.choice = choice;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

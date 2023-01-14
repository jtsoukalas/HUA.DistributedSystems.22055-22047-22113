package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="divorceStatement")
public class DivorceStatement implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="person_id")
    private User person;

    // todo convert String to Enum
    @Column(name="faculty")
    private String faculty;

    @Column(name="comment")
    private String comment;

    // todo convert String to Boolean for Agreement
    @Column(name="agreement")
    private String agreement;

    @Column(name="timestamp")
    private Date timestamp;


    public DivorceStatement(){}
    public DivorceStatement(String id, User person, String faculty, String comment, String agreement, Date timestamp) {
        this.id = id;
        this.person = person;
        this.faculty = faculty;
        this.comment = comment;
        this.agreement = agreement;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
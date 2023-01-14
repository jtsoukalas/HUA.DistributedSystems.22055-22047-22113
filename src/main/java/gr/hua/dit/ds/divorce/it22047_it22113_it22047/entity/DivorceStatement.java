package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="divorceStatement")
public class DivorceStatement implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="person_id")
    private User person;

    @Column(name="faculty")
    private String faculty;

    @Column(name="comment")
    private String comment;

    @Column(name="agreement")
    private String agreement;

    @Column(name="timestamp")
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "divorce_id")
    private Divorce divorce;

//    public String getDivorceId() {
//        return divorceId;
//    }
//
//    public void setDivorceId(String divorceId) {
//        this.divorceId = divorceId;
//    }


    public DivorceStatement(){}
//    public DivorceStatement(Integer id, User person, String faculty, String comment, String agreement, Date timestamp) {
//        this.id = id;
//        this.person = person;
//        this.faculty = faculty;
//        this.comment = comment;
//        this.agreement = agreement;
//        this.timestamp = timestamp;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

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
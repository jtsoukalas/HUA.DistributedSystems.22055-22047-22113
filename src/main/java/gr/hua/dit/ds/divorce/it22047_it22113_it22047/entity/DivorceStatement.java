package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    @Enumerated(EnumType.STRING)
    @Column(name="faculty")
    @NotNull
    private Faculty faculty;

    @Column(name="comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name="statement_status")
    @Column(name="choice")
    private DivorceStatementStatus choice;

    @Column(name="timestamp")
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "divorce_id")
    private Divorce divorce;


    public DivorceStatement(){}

    public DivorceStatement(Integer id, User person, Faculty faculty, String comment, DivorceStatementStatus choice, Date timestamp, Divorce divorce) {
        this.id = id;
        this.person = person;
        this.faculty = faculty;
        this.comment = comment;
        this.choice = choice;
        this.timestamp = timestamp;
        this.divorce = divorce;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public Faculty getFaculty() {return faculty;}

    public void setFaculty(Faculty faculty) {this.faculty = faculty;}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DivorceStatementStatus getChoice() {
        return choice;
    }

    public void setChoice(DivorceStatementStatus agreement) {
        this.choice = agreement;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
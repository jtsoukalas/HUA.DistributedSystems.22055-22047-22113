package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToOne(cascade=CascadeType.PERSIST)
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
    private DivorceStatementChoice choice;

    @Column(name="timestamp")
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "divorce_id")
    private Divorce divorce;


    public DivorceStatement(User user){}

    public DivorceStatement(Integer id, User person, Faculty faculty, String comment, DivorceStatementChoice choice, Date timestamp, Divorce divorce) {
        this.id = id;
        this.person = person;
        this.faculty = faculty;
        this.comment = comment;
        this.choice = choice;
        this.timestamp = timestamp;
        this.divorce = divorce;
    }

    public DivorceStatement(User person, Faculty faculty, DivorceStatementChoice choice, Divorce divorce) {
        this.person = person;
        this.faculty = faculty;
        this.choice = choice;
        this.divorce = divorce;
    }

    public DivorceStatement() {
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

    public DivorceStatementChoice getChoice() {
        return choice;
    }

    public void setChoice(DivorceStatementChoice agreement) {
        this.choice = agreement;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DivorceStatement)) return false;
        DivorceStatement that = (DivorceStatement) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPerson(), that.getPerson()) && getFaculty() == that.getFaculty() && Objects.equals(getComment(), that.getComment()) && getChoice() == that.getChoice() && Objects.equals(getTimestamp(), that.getTimestamp()) && Objects.equals(divorce, that.divorce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getFaculty(), getComment(), getChoice(), getTimestamp(), divorce);
    }
}
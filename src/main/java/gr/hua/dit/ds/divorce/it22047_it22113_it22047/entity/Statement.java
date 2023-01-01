package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="statement")
public class Statement implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

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


}

package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="divorce")
public class Divorce implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="status")
    private DivorceStatus status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="lead_lawyer_id")
    private User leadLawyer;

    @Column(name="contract_details")
    private String contractDetails;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "statement")
    private List<Statement> statement;

    @Column(name="notarial_act_number")
    private String notarialDeedNumber;

    @Column(name="submit_date")
    private Date submitDate;

    @Column(name="application_timest")
    private Date applicationDate;

    public List<Statement> getStatement() {
        return statement;
    }


}

package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import jakarta.persistence.*;

import java.io.Serializable;
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
    private List<DivorceStatement> statement;

    @Column(name="notarial_act_number")
    private String notarialDeedNumber;

    @Column(name="submit_date")
    private Date submitDate;

    @Column(name="application_timest")
    private Date applicationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DivorceStatus getStatus() {
        return status;
    }

    public void setStatus(DivorceStatus status) {
        this.status = status;
    }

    public User getLeadLawyer() {
        return leadLawyer;
    }

    public void setLeadLawyer(User leadLawyer) {
        this.leadLawyer = leadLawyer;
    }

    public String getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(String contractDetails) {
        this.contractDetails = contractDetails;
    }

    public List<DivorceStatement> getStatement() {
        return statement;
    }

    public void setStatement(List<DivorceStatement> statement) {
        this.statement = statement;
    }

    public String getNotarialDeedNumber() {
        return notarialDeedNumber;
    }

    public void setNotarialDeedNumber(String notarialDeedNumber) {
        this.notarialDeedNumber = notarialDeedNumber;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}
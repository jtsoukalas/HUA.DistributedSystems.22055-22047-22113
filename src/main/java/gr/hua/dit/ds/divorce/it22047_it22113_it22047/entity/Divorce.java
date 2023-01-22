package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "divorce")
public class Divorce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DivorceStatus status;

    @OneToOne() //cascade = CascadeType.PERSIST
    @JoinColumn(name = "lead_lawyer_id")
    private User leadLawyer;

    @Column(name = "contract_details")
    private String contractDetails;


//    cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.DETACH}
    @OneToMany(mappedBy = "divorce",fetch = FetchType.LAZY)
//    @JoinColumn(name = "divorceStatement_divorce_id")
    private List<DivorceStatement> statement;

    @Column(name = "notarial_act_number")
    private String notarialDeedNumber;

    @Column(name = "submit_date")
    private Date submitDate;

    @Column(name = "application_timest")
    private Date applicationDate;


    public Divorce(Integer id, DivorceStatus status, User leadLawyer, String contractDetails, List<DivorceStatement> statement, String notarialDeedNumber, Date submitDate, Date applicationDate) {
        this.id = id;
        this.status = status;
        this.leadLawyer = leadLawyer;
        this.contractDetails = contractDetails;
        this.statement = statement;
        this.notarialDeedNumber = notarialDeedNumber;
        this.submitDate = submitDate;
        this.applicationDate = applicationDate;
    }

    public Divorce() {}

    public boolean isAllStatementsAccepted(){
        for(DivorceStatement divorceStatement : statement){
            if(!divorceStatement.equals(DivorceStatementStatus.ACCEPT)){
                return false;
            }
        }
        return true;
    }

    public boolean isReadyForNotarialAct(){
        int countAcceptStatements = 0;
        for(DivorceStatement divorceStatement : statement){
            if(divorceStatement.getChoice().equals(DivorceStatementStatus.ACCEPT)){
                countAcceptStatements++;
            }
        }
        return countAcceptStatements == 3;
    }

    public boolean isClosed(){
        return status.equals(DivorceStatus.COMPLETED) || status.equals(DivorceStatus.CANCELLED);
    }

    public void changeAllStatementsToPending(){
        for(DivorceStatement divorceStatement : statement){
            divorceStatement.setChoice(DivorceStatementStatus.PENDING);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<DivorceStatement> getStatement() {return statement;}

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
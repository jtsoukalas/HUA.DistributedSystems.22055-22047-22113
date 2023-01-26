package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
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
    @NotNull
    private DivorceStatus status;

    @OneToOne() //cascade = CascadeType.PERSIST **
    @JoinColumn(name = "lead_lawyer_id")
    private User leadLawyer;

    @Column(name = "contract_details")
    private String contractDetails;


//    cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.DETACH}
    @OneToMany(mappedBy = "divorce",fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH}) //**
//    @JoinColumn(name = "divorceStatement_divorce_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<DivorceStatement> statement;

    @Column(name = "notarial_act_number")
    private String notarialDeedNumber;

    @Column(name = "close_timestamp")
    private Date closeDate;

    @Column(name = "application_timestamp")
    private Date applicationDate;


    public Divorce(Integer id, DivorceStatus status, User leadLawyer, String contractDetails, List<DivorceStatement> statement, String notarialDeedNumber, Date submitDate, Date applicationDate) {
        this.id = id;
        this.status = status;
        this.leadLawyer = leadLawyer;
        this.contractDetails = contractDetails;
        this.statement = statement;
        this.notarialDeedNumber = notarialDeedNumber;
        this.closeDate = submitDate;
        this.applicationDate = applicationDate;
    }

    public Divorce() {}

    public boolean isAllStatementsAccepted(){
        for(DivorceStatement divorceStatement : statement){
            if(!divorceStatement.equals(DivorceStatementChoice.ACCEPT)){
                return false;
            }
        }
        return true;
    }

    public boolean isReadyForNotarialAct(){
        int countAcceptStatements = 0;
        for(DivorceStatement divorceStatement : statement){
            if(divorceStatement.getChoice().equals(DivorceStatementChoice.ACCEPT)){
                countAcceptStatements++;
            }
        }
        return countAcceptStatements == 3;
    }

    public boolean isClosed(){
        return status.equals(DivorceStatus.COMPLETED) || status.equals(DivorceStatus.CANCELLED);
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

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Divorce)) return false;
//        Divorce divorce = (Divorce) o;
//        return getId().equals(divorce.getId()) && getStatus() == divorce.getStatus() && getLeadLawyer().equals(divorce.getLeadLawyer()) && Objects.equals(getContractDetails(), divorce.getContractDetails()) && Objects.equals(getStatement(), divorce.getStatement()) && Objects.equals(getNotarialDeedNumber(), divorce.getNotarialDeedNumber()) && Objects.equals(getCloseDate(), divorce.getCloseDate()) && Objects.equals(getApplicationDate(), divorce.getApplicationDate());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getStatus(), getLeadLawyer(), getContractDetails(), getStatement(), getNotarialDeedNumber(), getCloseDate(), getApplicationDate());
//    }

    public boolean isStatementsValid(){
        List<Integer> addedPersons = new ArrayList<>();
        for (DivorceStatement statement : statement) {
            if(addedPersons.contains(statement.getPerson().getTaxNumber())){
                throw new IllegalArgumentException("Person with tax number " + statement.getPerson().getTaxNumber() + " is already added on that divorce application as involved party");
            } else {
                addedPersons.add(statement.getPerson().getTaxNumber());
            }
            if(!statement.getChoice().equals(DivorceStatementChoice.PENDING)){
                throw new IllegalArgumentException("Statement status for "+statement.getPerson().getTaxNumber() + " is " + statement.getChoice() + " should be 'PENDING' when creating divorce application");
            }
        }
        return true;
    }
}
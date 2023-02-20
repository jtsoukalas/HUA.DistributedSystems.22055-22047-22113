package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

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
    @OneToMany(mappedBy = "divorce", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
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

    public Divorce() {
    }


    public boolean isAllStatementsAccepted() {
        for (DivorceStatement divorceStatement : statement) {
            if (!divorceStatement.equals(DivorceStatementChoice.ACCEPT)) {
                return false;
            }
        }
        return true;
    }

    public boolean search(String query) {
        List<User> searchUsers = new ArrayList<>();
        searchUsers.add(leadLawyer);
        searchUsers.add(getLawyerTwo());
        searchUsers.add(getNotary());
        searchUsers.add(getSpouseOne());
        searchUsers.add(getSpouseTwo());

        for (User u : searchUsers) {
            if (u != null) {
                String s = u.getFullName();
                if (s.toLowerCase().contains(query.toLowerCase())) {
                    return true;
                }
                Integer i = u.getTaxNumber();
                if (i.toString().contains(query)) {
                    return true;
                }
            }
        }

        if (notarialDeedNumber != null) {
            if (notarialDeedNumber.toLowerCase().contains(query.toLowerCase())) {
                return true;
            }
        }
        if (contractDetails != null) {
            if (contractDetails.toLowerCase().contains(query.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public boolean isReadyForNotarialAct() {
        int countAcceptStatements = 0;
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getChoice().equals(DivorceStatementChoice.ACCEPT)) {
                countAcceptStatements++;
            }
        }
        return countAcceptStatements == 3;
    }

    public boolean isClosed() {
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

    public User getLawyerLead() {
        return leadLawyer;
    }

    public void setLawyerLead(User lawyerLead) {
        this.leadLawyer = lawyerLead;
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
//        return getId().equals(divorce.getId()) && getStatus() == divorce.getStatus() && getLawyerLead().equals(divorce.getLawyerLead()) && Objects.equals(getContractDetails(), divorce.getContractDetails()) && Objects.equals(getStatement(), divorce.getStatement()) && Objects.equals(getNotarialDeedNumber(), divorce.getNotarialDeedNumber()) && Objects.equals(getCloseDate(), divorce.getCloseDate()) && Objects.equals(getDate(), divorce.getDate());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getStatus(), getLawyerLead(), getContractDetails(), getStatement(), getNotarialDeedNumber(), getCloseDate(), getDate());
//    }

//    public boolean isStatementsValid() {
//        List<Integer> addedPersons = new ArrayList<>();
//        for (DivorceStatement statement : statement) {
//            if (addedPersons.contains(statement.getPerson().getTaxNumber())) {
//                throw new IllegalArgumentException("Person with tax number " + statement.getPerson().getTaxNumber() + " is already added on that divorce application as involved party");
//            } else {
//                addedPersons.add(statement.getPerson().getTaxNumber());
//            }
//            if (!statement.getChoice().equals(DivorceStatementChoice.PENDING)) {
//                throw new IllegalArgumentException("Statement status for " + statement.getPerson().getTaxNumber() + " is " + statement.getChoice() + " should be 'PENDING' when creating divorce application");
//            }
//        }
//        return true;
//    }

    public User getUser(Faculty faculty) throws UserWithWrongRoleException {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(faculty)) {
                return divorceStatement.getPerson();
            }
        }
        throw new UserWithWrongRoleException("User with role " + faculty + " is not found on divorce application");
    }

    public User getNotary() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.NOTARY)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public List<User> getSpouses() {
        List<User> spouses = new ArrayList<>();
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE)) {
                spouses.add(divorceStatement.getPerson());
            }
        }
        return spouses;
    }

    public User getSpouseOne() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_ONE)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public User getSpouseTwo() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_TWO)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public User getLawyerTwo() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.LAWYER_TWO)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public DivorceStatementChoice getSpouseOneChoice() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_ONE)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }

    public DivorceStatementChoice getSpouseTwoChoice() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_TWO)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }

    public DivorceStatementChoice getLawyerTwoChoice() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.LAWYER_TWO)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }

    public DivorceStatementChoice getNotaryChoice() {
        for (DivorceStatement divorceStatement : statement) {
            if (divorceStatement.getFaculty().equals(Faculty.NOTARY)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }

}
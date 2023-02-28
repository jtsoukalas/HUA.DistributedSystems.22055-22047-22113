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

    @Column(name = "contract_details", length = 10000)
    private String contractDetails;


    //    cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.DETACH}
    @OneToMany(mappedBy = "divorce", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH}) //**
//    @JoinColumn(name = "divorceStatement_divorce_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<DivorceStatement> statements;

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
        this.statements = statement;
        this.notarialDeedNumber = notarialDeedNumber;
        this.closeDate = submitDate;
        this.applicationDate = applicationDate;
    }


    public Divorce() {
    }


    public boolean isAllStatementsAccepted() {
        for (DivorceStatement divorceStatement : statements) {
            if (!divorceStatement.equals(DivorceStatementChoice.ACCEPTED)) {
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

    public void removeAllDivorceStatement(DivorceStatement statement){
        this.statements.remove(statement);
    }

    public void removeAllDivorceStatement(List<DivorceStatement> statements){
        statements.forEach(statement -> removeAllDivorceStatement(statement));
    }

    public void removeAllDivorceStatements(){
        statements = new ArrayList<>();
    }

    public void addDivorceStatement(DivorceStatement statement){
        this.statements.add(statement);
    }

    public void addAllDivorceStatement(List<DivorceStatement> statements){
        this.statements.addAll(statements);
    }

    public boolean isReadyForNotarialAct() {
        int countAcceptStatements = 0;
        try{
            for (DivorceStatement divorceStatement : statements) {
                if (divorceStatement.getChoice().equals(DivorceStatementChoice.ACCEPTED)) {
                    countAcceptStatements++;
                }
            }
        } catch (NullPointerException e){
            return false;
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

    public List<DivorceStatement> getStatements() {
        return statements;
    }

    public DivorceStatement getStatement(User user) {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getPerson().equals(user)) {
                return divorceStatement;
            }
        }
        return null;
    }

    public void setStatements(List<DivorceStatement> statements) {
        this.statements = statements;
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
//        return getId().equals(divorce.getId()) && getStatus() == divorce.getStatus() && getLawyerLead().equals(divorce.getLawyerLead()) && Objects.equals(getContractDetails(), divorce.getContractDetails()) && Objects.equals(getStatements(), divorce.getStatements()) && Objects.equals(getNotarialDeedNumber(), divorce.getNotarialDeedNumber()) && Objects.equals(getCloseDate(), divorce.getCloseDate()) && Objects.equals(getDate(), divorce.getDate());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getStatus(), getLawyerLead(), getContractDetails(), getStatements(), getNotarialDeedNumber(), getCloseDate(), getDate());
//    }

//    public boolean isStatementsValid() {
//        List<Integer> addedPersons = new ArrayList<>();
//        for (DivorceStatement statements : statements) {
//            if (addedPersons.contains(statements.getPerson().getTaxNumber())) {
//                throw new IllegalArgumentException("Person with tax number " + statements.getPerson().getTaxNumber() + " is already added on that divorce application as involved party");
//            } else {
//                addedPersons.add(statements.getPerson().getTaxNumber());
//            }
//            if (!statements.getChoice().equals(DivorceStatementChoice.PENDING)) {
//                throw new IllegalArgumentException("Statement status for " + statements.getPerson().getTaxNumber() + " is " + statements.getChoice() + " should be 'PENDING' when creating divorce application");
//            }
//        }
//        return true;
//    }

    public boolean checkIfUserIsSpouse(User user) {
        return getSpouseOne().equals(user) || getSpouseTwo().equals(user);
    }

    public User getUserFromStatements(Faculty faculty) throws UserWithWrongRoleException {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(faculty)) {
                return divorceStatement.getPerson();
            }
        }
        throw new UserWithWrongRoleException("User with role " + faculty + " is not found on divorce application");
    }

    public DivorceStatement getStatement (Faculty faculty) throws UserWithWrongRoleException {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(faculty)) {
                return divorceStatement;
            }
        }
        throw new UserWithWrongRoleException("User with role " + faculty + " is not found on divorce application");
    }

    public User getUserFromStatements(Role role) throws UserWithWrongRoleException {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().getRole().equals(role)) {
                return divorceStatement.getPerson();
            }
        }
        throw new UserWithWrongRoleException("User with role " + role + " is not found on divorce application");
    }

    public Faculty getFacultyFromStatements(Role role) throws UserWithWrongRoleException {
        List<Faculty> searchFaculties = new ArrayList<>();
        switch (role){
            case SPOUSE -> {
                searchFaculties.add(Faculty.SPOUSE_ONE);
                searchFaculties.add(Faculty.SPOUSE_TWO);
            }
            case LAWYER -> {
                searchFaculties.add(Faculty.LAWYER_TWO);
            }
            case NOTARY -> searchFaculties.add(Faculty.NOTARY);
        }

        for (DivorceStatement divorceStatement : statements) {
            if (searchFaculties.contains(divorceStatement.getFaculty())) {
                return divorceStatement.getFaculty();
            }
        }
        throw new UserWithWrongRoleException("User with role " + role + " is not found on divorce application");
    }

    public DivorceStatement getStatement(Role role) throws UserWithWrongRoleException {
        List<Faculty> searchFaculties = new ArrayList<>();
        switch (role){
            case SPOUSE -> {
                searchFaculties.add(Faculty.SPOUSE_ONE);
                searchFaculties.add(Faculty.SPOUSE_TWO);
            }
            case LAWYER -> {
                searchFaculties.add(Faculty.LAWYER_TWO);
            }
            case NOTARY -> searchFaculties.add(Faculty.NOTARY);
        }

        for (DivorceStatement divorceStatement : statements) {
            if (searchFaculties.contains(divorceStatement.getFaculty())) {
                return divorceStatement;
            }
        }
        throw new UserWithWrongRoleException("User with role " + role + " is not found on divorce application");
    }

    public User getUserFromStatements(Integer taxNumber) throws UserWithWrongRoleException {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getPerson().getTaxNumber().equals(taxNumber)) {
                return divorceStatement.getPerson();
            }
        }
        throw new UserWithWrongRoleException("User with taxNumber: "+taxNumber+" is not found on divorce application");
    }

    public User getNotary() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.NOTARY)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public List<User> getSpouses() {
        List<User> spouses = new ArrayList<>();
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_ONE)
                    || divorceStatement.getFaculty().equals(Faculty.SPOUSE_TWO)) {
                spouses.add(divorceStatement.getPerson());
            }
        }
        return spouses;
    }

    public User getSpouseOne() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_ONE)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public User getSpouseTwo() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_TWO)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public User getLawyerTwo() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.LAWYER_TWO)) {
                return divorceStatement.getPerson();
            }
        }
        return null;
    }

    public DivorceStatementChoice getSpouseOneChoice() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_ONE)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }


    public DivorceStatementChoice getSpouseTwoChoice() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.SPOUSE_TWO)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }

    public DivorceStatementChoice getLawyerTwoChoice() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.LAWYER_TWO)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }

    public DivorceStatementChoice getNotaryChoice() {
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getFaculty().equals(Faculty.NOTARY)) {
                return divorceStatement.getChoice();
            }
        }
        return null;
    }

    public User getLeadLawyer() {
        return leadLawyer;
    }

    public void setLeadLawyer(User leadLawyer) {
        this.leadLawyer = leadLawyer;
    }

    public boolean isPublic() {
        return !this.status.equals(DivorceStatus.DRAFT);
    }

    public boolean hasAccess(Integer taxNumber){
        if (leadLawyer.getTaxNumber().equals(taxNumber)){
            return true;
        }
        for (DivorceStatement divorceStatement : statements) {
            if (divorceStatement.getPerson().getTaxNumber().equals(taxNumber)) {
                return true;
            }
        }
        return false;
    }

    public void updateDivorceStatus(){
        for (DivorceStatement statement : statements)
            if (statement.getChoice().equals(DivorceStatementChoice.REJECTED)){
                this.status = DivorceStatus.CANCELLED;
               break;
            } else if (statement.getChoice().equals(DivorceStatementChoice.PENDING) || statement.getChoice().equals(DivorceStatementChoice.REJECTED) || statement.getChoice().equals(DivorceStatementChoice.WAITING)){
                this.status = DivorceStatus.PENDING;
                break;
            } else {
                this.status = DivorceStatus.COMPLETED;
            }
    }
}
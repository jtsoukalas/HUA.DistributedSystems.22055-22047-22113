package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatementChoice;

import java.util.Date;
import java.util.List;

public class DivorceAPIResponse extends DivorceAPIResponseConcise {

    private String contractDetails;
    private Date date;

    private String lawyerLeadName;
    private String lawyerName;
    private String notaryName;
    private String notarialDeedNumber;

    private DivorceStatementChoice lawyerTwoChoice;
    private DivorceStatementChoice spouseOneChoice;
    private DivorceStatementChoice spouseTwoChoice;
    private DivorceStatementChoice notaryChoice;

    private List<DivorceStatementAPIResponse> statements;

    public DivorceAPIResponse (Divorce divorce){
        super(divorce);
        this.contractDetails = divorce.getContractDetails();
        this.date = divorce.getApplicationDate();
        try {
            this.lawyerLeadName = divorce.getLawyerLead().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.notaryName = divorce.getNotary().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.lawyerName = divorce.getLawyerTwo().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.notarialDeedNumber = divorce.getNotarialDeedNumber();
        } catch (NullPointerException e) {
        }
        try {
            this.lawyerTwoChoice = divorce.getLawyerTwoChoice();
        } catch (NullPointerException e) {
        }
        try {
            this.spouseOneChoice = divorce.getSpouseOneChoice();
        } catch (NullPointerException e) {
        }
        try {
            this.spouseTwoChoice = divorce.getSpouseTwoChoice();
        } catch (NullPointerException e) {
        }
        try {
            this.notaryChoice = divorce.getNotaryChoice();
        } catch (NullPointerException e) {
        }
    }

    public String getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(String contractDetails) {
        this.contractDetails = contractDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLawyerLeadName() {
        return lawyerLeadName;
    }

    public void setLawyerLeadName(String lawyerLeadName) {
        this.lawyerLeadName = lawyerLeadName;
    }

    public String getNotaryName() {
        return notaryName;
    }

    public void setNotaryName(String notaryName) {
        this.notaryName = notaryName;
    }

    public String getLawyerName() {
        return lawyerName;
    }

    public void setLawyerName(String lawyerName) {
        this.lawyerName = lawyerName;
    }

    public String getNotarialDeedNumber() {
        return notarialDeedNumber;
    }

    public void setNotarialDeedNumber(String notarialDeedNumber) {
        this.notarialDeedNumber = notarialDeedNumber;
    }

    public DivorceStatementChoice getLawyerTwoChoice() {
        return lawyerTwoChoice;
    }

    public void setLawyerTwoChoice(DivorceStatementChoice lawyerTwoChoice) {
        this.lawyerTwoChoice = lawyerTwoChoice;
    }

    public DivorceStatementChoice getSpouseOneChoice() {
        return spouseOneChoice;
    }

    public void setSpouseOneChoice(DivorceStatementChoice spouseOneChoice) {
        this.spouseOneChoice = spouseOneChoice;
    }

    public DivorceStatementChoice getSpouseTwoChoice() {
        return spouseTwoChoice;
    }

    public void setSpouseTwoChoice(DivorceStatementChoice spouseTwoChoice) {
        this.spouseTwoChoice = spouseTwoChoice;
    }

    public DivorceStatementChoice getNotaryChoice() {
        return notaryChoice;
    }

    public void setNotaryChoice(DivorceStatementChoice notaryChoice) {
        this.notaryChoice = notaryChoice;
    }

    public List<DivorceStatementAPIResponse> getStatements() {
        return statements;
    }

    public void setStatements(List<DivorceStatementAPIResponse> statements) {
        this.statements = statements;
    }
}



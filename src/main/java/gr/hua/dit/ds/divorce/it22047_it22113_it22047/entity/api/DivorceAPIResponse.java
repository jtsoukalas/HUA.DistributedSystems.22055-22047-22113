package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatementChoice;

import java.util.Date;
import java.util.List;

public class DivorceAPIResponse extends DivorceAPIResponseConcise {

    private String contractDetails;
    private Date date;

    private String lawyerLeadName;
    private String notarialDeedNumber;

    private List<DivorceStatementAPIResponse> statements;

//    private List<DivorceStatementAPIResponse> statements;

    public DivorceAPIResponse (Divorce divorce){
        super(divorce);
        this.contractDetails = divorce.getContractDetails();
        this.date = divorce.getApplicationDate();
        try {
            this.lawyerLeadName = divorce.getLawyerLead().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.notarialDeedNumber = divorce.getNotarialDeedNumber();
        } catch (NullPointerException e) {
        }
        this.statements = DivorceStatementAPIResponse.fromList(divorce.getStatement());
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

    public String getNotarialDeedNumber() {
        return notarialDeedNumber;
    }

    public void setNotarialDeedNumber(String notarialDeedNumber) {
        this.notarialDeedNumber = notarialDeedNumber;
    }

//    public List<DivorceStatementAPIResponse> getStatements() {
//        return statements;
//    }
//
//    public void setStatements(List<DivorceStatementAPIResponse> statements) {
//        this.statements = statements;
//    }
    
    public List<DivorceStatementAPIResponse> getStatements() {
        return statements;
    }

    public void setStatements(List<DivorceStatementAPIResponse> statements) {
        this.statements = statements;
    }
}



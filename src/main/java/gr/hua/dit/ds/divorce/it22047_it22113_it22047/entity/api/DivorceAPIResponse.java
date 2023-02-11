package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;

import java.util.Date;

public class DivorceAPIResponse extends DivorceAPIResponseConcise {

    private String contractDetails;
    private Date date;

    private String lawyerLeadName;
    private String lawyerName;
    private String notaryName;

    public DivorceAPIResponse(Integer id, String status, String spouseOneName, String spouseTwoName) {
        super(id, status, spouseOneName, spouseTwoName);
    }
    public DivorceAPIResponse(Integer id, String status, String spouseOneName, String spouseTwoName, String contractDetails, Date applicationDate, String lawyerLeadName, String lawyerName, String notaryName) {
        super(id, status, spouseOneName, spouseTwoName);
        this.contractDetails = contractDetails;
        this.date = applicationDate;
        this.lawyerLeadName = lawyerLeadName;
        this.lawyerName = lawyerName;
        this.notaryName = notaryName;
    }
    public DivorceAPIResponse (Divorce divorce){
        super(divorce);
        this.contractDetails = divorce.getContractDetails();
        this.date = divorce.getApplicationDate();
        try {
            this.lawyerLeadName = divorce.getLeadLawyer().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.notaryName = divorce.getNotary().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.lawyerName = divorce.getLawyer().getFullName();
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
}



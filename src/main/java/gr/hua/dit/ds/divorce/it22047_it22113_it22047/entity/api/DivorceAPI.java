package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatus;

import java.util.Date;

public class DivorceAPI {

    private Integer id;
    // private boolean draft;
    private DivorceStatus status;
    private String contractDetails;
    private Date applicationDate;

    private Integer lawyerLeadTaxNumber;
    private Integer lawyerTwoTaxNumber;
    private Integer spouseOneTaxNumber;
    private Integer spouseTwoTaxNumber;
    private Integer notaryTaxNumber;


    public DivorceAPI(Divorce divorce) {
        id = divorce.getId();
        status = divorce.getStatus();
        contractDetails = divorce.getContractDetails();
        applicationDate = divorce.getApplicationDate();
        try {
            lawyerLeadTaxNumber = divorce.getLeadLawyer().getTaxNumber();
        } catch (NullPointerException e) {
        }
        try {
            notaryTaxNumber = divorce.getNotary().getTaxNumber();
        } catch (NullPointerException e) {
        }
        try {
            spouseOneTaxNumber = divorce.getSpouseOne().getTaxNumber();
        } catch (NullPointerException e) {
        }
        try {
            spouseTwoTaxNumber = divorce.getSpouseTwo().getTaxNumber();
        } catch (NullPointerException e) {
        }
    }

    public Integer getNotaryTaxNumber() {
        return notaryTaxNumber;
    }

    public void setNotaryTaxNumber(Integer notaryTaxNumber) {
        this.notaryTaxNumber = notaryTaxNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(String contractDetails) {
        this.contractDetails = contractDetails;
    }

    public Integer getLawyerLeadTaxNumber() {
        return lawyerLeadTaxNumber;
    }

    public void setLawyerLeadTaxNumber(Integer lawyerLeadTaxNumber) {
        this.lawyerLeadTaxNumber = lawyerLeadTaxNumber;
    }

    public Integer getLawyerTwoTaxNumber() {
        return lawyerTwoTaxNumber;
    }

    public void setLawyerTwoTaxNumber(Integer lawyerTwoTaxNumber) {
        this.lawyerTwoTaxNumber = lawyerTwoTaxNumber;
    }

    public Integer getSpouseOneTaxNumber() {
        return spouseOneTaxNumber;
    }

    public void setSpouseOneTaxNumber(Integer spouseOneTaxNumber) {
        this.spouseOneTaxNumber = spouseOneTaxNumber;
    }

    public Integer getSpouseTwoTaxNumber() {
        return spouseTwoTaxNumber;
    }

    public void setSpouseTwoTaxNumber(Integer spouseTwoTaxNumber) {
        this.spouseTwoTaxNumber = spouseTwoTaxNumber;
    }

    public DivorceStatus getStatus() {
        return status;
    }

    public void setStatus(DivorceStatus status) {
        this.status = status;
    }

    public boolean isDraft() {
        return status.equals(DivorceStatus.DRAFT);
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}

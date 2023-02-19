package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatus;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Faculty;

public class DivorceAPIRequest {

    private Integer id;
    private DivorceStatus status;
    private String contractDetails;

    private Integer lawyerLeadTaxNumber;
    private Integer lawyerTwoTaxNumber;
    private Integer spouseOneTaxNumber;
    private Integer spouseTwoTaxNumber;
    private Integer notaryTaxNumber;

    public DivorceAPIRequest (Divorce divorce) {
        id = divorce.getId();
        status = divorce.getStatus();
        contractDetails = divorce.getContractDetails();
        try {
            lawyerLeadTaxNumber = divorce.getLawyerLead().getTaxNumber();
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

    public DivorceAPIRequest(Integer id, DivorceStatus status, String contractDetails, Integer lawyerLeadTaxNumber, Integer lawyerTwoTaxNumber, Integer spouseOneTaxNumber, Integer spouseTwoTaxNumber, Integer notaryTaxNumber) {
        this.id = id;
        this.status = status;
        this.contractDetails = contractDetails;
        this.lawyerLeadTaxNumber = lawyerLeadTaxNumber;
        this.lawyerTwoTaxNumber = lawyerTwoTaxNumber;
        this.spouseOneTaxNumber = spouseOneTaxNumber;
        this.spouseTwoTaxNumber = spouseTwoTaxNumber;
        this.notaryTaxNumber = notaryTaxNumber;
    }

    public Integer getUserTaxNumber(Faculty faculty){
        switch (faculty) {
            case LAWYER_LEAD:
            case LAWYER_TWO:
                return lawyerLeadTaxNumber;
            case NOTARY:
                return notaryTaxNumber;
            case SPOUSE_ONE:
                return spouseOneTaxNumber;
            case SPOUSE_TWO:
                return spouseTwoTaxNumber;
            default:
                return null;
        }
    }

    public void completenessCheck() {
        if(id != null && status != null && contractDetails != null && lawyerLeadTaxNumber != null && lawyerTwoTaxNumber != null && spouseOneTaxNumber != null && spouseTwoTaxNumber != null && notaryTaxNumber != null) {
            return;
        }
        throw new IllegalArgumentException("DivorceAPIRequest is not complete");
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
    }

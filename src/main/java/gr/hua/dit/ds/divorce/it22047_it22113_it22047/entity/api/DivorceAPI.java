package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;

public class DivorceAPI {

    private Integer id;
    private boolean draft;
    private String contractDetails;

    private Integer lawyerLeadTaxNumber;
    private Integer lawyerTwoTaxNumber;
    private Integer spouseOneTaxNumber;
    private Integer spouseTwoTaxNumber;
    private Integer notaryTaxNumber;

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

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }
}

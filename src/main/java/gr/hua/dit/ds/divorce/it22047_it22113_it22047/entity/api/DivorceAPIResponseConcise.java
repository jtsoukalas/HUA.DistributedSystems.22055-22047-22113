package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api;


import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;

public class DivorceAPIResponseConcise {

    private Integer id;
    private String status;
    private String spouseOneName;
    private String spouseTwoName;
    private boolean isUserLeadLawyer;

    public DivorceAPIResponseConcise(Integer id, String status, String spouseOneName, String spouseTwoName) {
        this.id = id;
        this.status = status;
        this.spouseOneName = spouseOneName;
        this.spouseTwoName = spouseTwoName;
    }

    public DivorceAPIResponseConcise(Integer id, String status, String spouseOneName, String spouseTwoName, boolean isUserLeadLawyer) {
        this.id = id;
        this.status = status;
        this.spouseOneName = spouseOneName;
        this.spouseTwoName = spouseTwoName;
        this.isUserLeadLawyer = isUserLeadLawyer;
    }

    public DivorceAPIResponseConcise(Divorce divorce, Integer userTaxNumber) {
        this.id = divorce.getId();
        this.status = divorce.getStatus().toHumanReadable();
        try {
            this.spouseOneName = divorce.getSpouseOne().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.spouseTwoName = divorce.getSpouseTwo().getFullName();
        } catch (NullPointerException e) {
        }
        try {
            this.isUserLeadLawyer = divorce.getLawyerLead().getTaxNumber().equals(userTaxNumber);
        } catch (NullPointerException e) {
            this.isUserLeadLawyer = false;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpouseOneName() {
        return spouseOneName;
    }

    public void setSpouseOneName(String spouseOneName) {
        this.spouseOneName = spouseOneName;
    }

    public String getSpouseTwoName() {
        return spouseTwoName;
    }

    public void setSpouseTwoName(String spouseTwoName) {
        this.spouseTwoName = spouseTwoName;
    }

    public boolean isUserLeadLawyer() {
        return isUserLeadLawyer;
    }

    public void setUserLeadLawyer(boolean userLeadLawyer) {
        isUserLeadLawyer = userLeadLawyer;
    }
}



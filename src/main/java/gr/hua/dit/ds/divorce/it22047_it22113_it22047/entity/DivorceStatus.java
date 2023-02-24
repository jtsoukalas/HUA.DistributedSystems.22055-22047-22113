package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

public enum DivorceStatus {
    DRAFT("Draft"),
    COMPLETED("Completed"),
    PENDING("Pending"),
    CANCELLED("Cancelled");

    private final String status;

    DivorceStatus(String status) {
        this.status = status;
    }

    public String toHumanReadable() {
        return status;
    }



}
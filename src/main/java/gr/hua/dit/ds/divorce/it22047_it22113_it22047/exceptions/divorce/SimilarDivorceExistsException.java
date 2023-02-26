package gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;

public class SimilarDivorceExistsException extends Exception {
    Divorce similarDivorce;

    public SimilarDivorceExistsException(Divorce similarDivorce) {
        this.similarDivorce = similarDivorce;
    }

    @Override
    public String getMessage() {
        return "Divorce between " + similarDivorce.getSpouseOne().getFullName() + " (" + similarDivorce.getSpouseOne().getTaxNumber() + ") and " + similarDivorce.getSpouseTwo().getFullName() + " (" + similarDivorce.getSpouseTwo().getTaxNumber() + ") already exists and it's in status: " + (similarDivorce.getStatus().toHumanReadable());
    }
}

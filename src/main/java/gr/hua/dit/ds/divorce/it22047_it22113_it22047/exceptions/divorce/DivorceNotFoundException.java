package gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.DivorceAPIRequest;

public class DivorceNotFoundException extends RuntimeException {
    DivorceAPIRequest divorce;
    Integer id;

    public DivorceNotFoundException(DivorceAPIRequest divorce) {
        this.divorce = divorce;
    }

    public DivorceNotFoundException (Integer id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        try{
            return divorce.getId()!=null ? "Divorce (ID: "+divorce.getId()+") between spouse with taxNumber:" + divorce.getSpouseOneTaxNumber() + " and spouse with taxNumber:" + divorce.getSpouseTwoTaxNumber() + " not found" :
                    "Divorce between " + divorce.getSpouseOneTaxNumber() + " and " + divorce.getSpouseTwoTaxNumber() + " not found";
        } catch (NullPointerException e ){
            return "Divorce (ID: "+id+") was not found or user doesn't have access to it";
        }
    }
}

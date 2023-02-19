package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import java.util.ArrayList;
import java.util.List;

public enum Faculty {
    LAWYER_LEAD (Role.LAWYER),
    LAWYER_TWO (Role.LAWYER),
    LAWYER (Role.LAWYER),
    NOTARY (Role.NOTARY),
    SPOUSE (Role.SPOUSE),
    SPOUSE_TWO (Role.SPOUSE),
    SPOUSE_ONE (Role.SPOUSE);

    private Role role;

    Faculty (Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public static List<Faculty> getFaculties(){
        List<Faculty> faculties = new ArrayList<Faculty>();
        faculties.addAll(List.of(Faculty.values()));
        return faculties;
    }
}

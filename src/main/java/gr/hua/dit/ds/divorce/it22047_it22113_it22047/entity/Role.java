package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
        SPOUSE,
        LAWYER,
        NOTARY,
        ADMIN,
        SPOUSE_ADMIN,
        SPOUSE_LAWYER,
        SPOUSE_NOTARY;


        @Override
        public String getAuthority() {
                switch (this) {
                        case SPOUSE:
                                return "SPOUSE";
                        case LAWYER:
                                return "LAWYER";
                        case NOTARY:
                                return "NOTARY";
                        case ADMIN:
                                return "ADMIN";
                        case SPOUSE_ADMIN:
                                return "SPOUSE_ADMIN";
                        case SPOUSE_LAWYER:
                                return "SPOUSE_LAWYER";
                        case SPOUSE_NOTARY:
                                return "SPOUSE_NOTARY";
                }
                return null;
        }
}
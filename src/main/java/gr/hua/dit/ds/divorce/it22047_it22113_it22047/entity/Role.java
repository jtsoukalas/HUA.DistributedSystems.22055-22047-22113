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
                                return "ROLE_SPOUSE";
                        case LAWYER:
                                return "ROLE_LAWYER";
                        case NOTARY:
                                return "ROLE_NOTARY";
                        case ADMIN:
                                return "ROLE_ADMIN";
                        case SPOUSE_ADMIN:
                                return "ROLE_SPOUSE_ADMIN";
                        case SPOUSE_LAWYER:
                                return "ROLE_SPOUSE_LAWYER";
                        case SPOUSE_NOTARY:
                                return "ROLE_SPOUSE_NOTARY";
                }
                return null;
        }
}
package gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
        SPOUSE,
        LAWYER,
        NOTARY,
        ADMIN;

        @Override
        public String getAuthority() {
                return this.name();
        }
}
package challenge_1.enums;

import challenge_1.domain.roles.BuyerRole;
import challenge_1.domain.roles.SellerRole;
import org.springframework.security.core.GrantedAuthority;

public enum RoleType {
    BUYER(new BuyerRole()),
    SELLER(new SellerRole());

    private final GrantedAuthority authority;

    RoleType(GrantedAuthority authority) {
        this.authority = authority;
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }


    @Override
    public String toString() {
        return "ROLE_" + this.name();
    }
}

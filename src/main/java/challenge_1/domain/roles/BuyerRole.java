package challenge_1.domain.roles;

import challenge_1.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;

public class BuyerRole implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return RoleType.BUYER.toString();
    }
}

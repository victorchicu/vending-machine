package challenge_1.domain.roles;

import challenge_1.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;

public class SellerRole implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return RoleType.SELLER.toString();
    }
}

package challenge_1.auth;

import challenge_1.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    @Override
    public String getName() {
        if (this.getPrincipal() instanceof User) {
            return ((User) this.getPrincipal()).getId();
        }
        return (this.getPrincipal() == null) ? "" : this.getPrincipal().toString();
    }
}

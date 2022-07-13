package challenge_1.auth;

import challenge_1.exceptions.UserNotFoundException;
import challenge_1.services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return userService.findById(authentication.getName())
                .map(user -> {
                    if (!passwordEncoder.matches((CharSequence) authentication.getCredentials(), user.getPassword())) {
                        throw new BadCredentialsException("Bad credentials");
                    }
                    return new CustomAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
                })
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

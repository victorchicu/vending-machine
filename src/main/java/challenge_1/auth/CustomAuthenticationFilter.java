package challenge_1.auth;

import challenge_1.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username = obtainUsername(request);
        username = (username != null) ? username.trim() : "";
        return userService.findByUsername(username)
                .map(user -> {
                    String password = obtainPassword(request);
                    password = (password != null) ? password : "";
                    CustomAuthenticationToken authRequest = new CustomAuthenticationToken(user.getId(), password);
                    setDetails(request, authRequest);
                    return authenticationManager.authenticate(authRequest);
                })
                .orElseThrow(() -> {
                    throw new AuthenticationServiceException("Authentication fail due to not existing user");
                });
    }
}

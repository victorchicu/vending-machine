package challenge_1.converters;

import challenge_1.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserToAuthenticationConverter implements Converter<User, Authentication> {
    @Override
    public Authentication convert(User source) {
        return new UsernamePasswordAuthenticationToken(
                source.getUsername(),
                source.getPassword(),
                source.getAuthorities()
        );
    }
}
package challenge_1.converters;

import challenge_1.domain.User;
import challenge_1.dto.RegisterDto;
import challenge_1.enums.RoleType;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class RegisterDtoToUserConverter implements Converter<RegisterDto, User> {
    private final PasswordEncoder passwordEncoder;

    public RegisterDtoToUserConverter(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convert(RegisterDto source) {
        return new User(
                null,
                source.getUsername(),
                passwordEncoder.encode(source.getPassword()),
                new ArrayList<>(),
                source.getRoles().stream()
                        .map(RoleType::getAuthority)
                        .collect(Collectors.toSet())
        );
    }
}
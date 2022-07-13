package challenge_1.converters;

import challenge_1.domain.User;
import challenge_1.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        return new UserDto(source.getUsername(), source.getDeposit());
    }
}

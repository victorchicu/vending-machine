package challenge_1.repository.converters;

import challenge_1.domain.User;
import challenge_1.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToUserConverter implements Converter<UserEntity, User> {
    @Override
    public User convert(UserEntity source) {
        return new User(
                source.getId(),
                source.getUsername(),
                source.getPassword(),
                source.getDeposit(),
                source.getRoles()
        );
    }
}

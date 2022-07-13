package challenge_1.repository.converters;

import challenge_1.domain.User;
import challenge_1.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToEntityConverter implements Converter<User, UserEntity> {
    @Override
    public UserEntity convert(User source) {
        UserEntity entity = new UserEntity();
        return entity
                .setId(source.getId())
                .setRoles(source.getAuthorities())
                .setDeposit(source.getDeposit())
                .setUsername(source.getUsername())
                .setPassword(source.getPassword());
    }
}
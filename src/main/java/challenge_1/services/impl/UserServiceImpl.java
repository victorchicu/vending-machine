package challenge_1.services.impl;

import challenge_1.domain.User;
import challenge_1.entity.UserEntity;
import challenge_1.repository.UserRepository;
import challenge_1.services.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
import java.util.function.UnaryOperator;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public UserServiceImpl(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public User save(User user) {
        UserEntity entity = toUserEntity(user);
        entity = userRepository.save(entity);
        return toUser(entity);
    }

    @Override
    public Optional<User> update(Principal principal, UnaryOperator<User> operator) {
        return findById(principal.getName())
                .map(operator)
                .map(this::toUserEntity)
                .map(userRepository::save)
                .map(this::toUser);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id)
                .map(this::toUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::toUser);
    }

    private User toUser(UserEntity source) {
        return conversionService.convert(source, User.class);
    }

    private UserEntity toUserEntity(User source) {
        return conversionService.convert(source, UserEntity.class);
    }
}

package challenge_1.services;

import challenge_1.domain.User;

import java.security.Principal;
import java.util.Optional;
import java.util.function.UnaryOperator;

public interface UserService {
    User save(User user);

    Optional<User> update(Principal principal, UnaryOperator<User> operator);

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);
}
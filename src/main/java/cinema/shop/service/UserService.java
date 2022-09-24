package cinema.shop.service;

import cinema.shop.model.User;
import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByEmail(String email);

    User get(Long id);
}

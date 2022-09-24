package cinema.shop.dao;

import cinema.shop.model.User;
import java.util.Optional;

public interface UserDao {
    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> get(Long id);
}

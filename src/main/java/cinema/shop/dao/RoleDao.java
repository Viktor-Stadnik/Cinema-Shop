package cinema.shop.dao;

import cinema.shop.model.Role;
import java.util.Optional;

public interface RoleDao {
    Role save(Role role);

    Optional<Role> getByName(String roleName);
}

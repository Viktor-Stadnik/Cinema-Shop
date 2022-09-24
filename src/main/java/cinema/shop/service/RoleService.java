package cinema.shop.service;

import cinema.shop.model.Role;

public interface RoleService {
    Role save(Role role);

    Role getByName(String roleName);
}

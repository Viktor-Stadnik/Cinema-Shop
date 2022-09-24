package cinema.shop.config;

import cinema.shop.model.Role;
import cinema.shop.model.User;
import cinema.shop.service.RoleService;
import cinema.shop.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role(Role.RoleName.ADMIN);
        roleService.save(adminRole);

        Role userRole = new Role(Role.RoleName.USER);
        roleService.save(userRole);

        User johnAdmin = new User();
        johnAdmin.setEmail("john@i.ua");
        johnAdmin.setPassword("john1234");
        johnAdmin.setRoles(Set.of(adminRole));
        userService.save(johnAdmin);

        User meryUser = new User();
        meryUser.setEmail("mery@i.ua");
        meryUser.setPassword("mery1234");
        meryUser.setRoles(Set.of(userRole));
        userService.save(meryUser);
    }
}

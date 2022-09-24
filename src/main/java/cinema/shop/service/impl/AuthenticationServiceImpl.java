package cinema.shop.service.impl;

import cinema.shop.model.User;
import cinema.shop.service.AuthenticationService;
import cinema.shop.service.ShoppingCartService;
import cinema.shop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.save(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}

package cinema.shop.dao;

import cinema.shop.model.ShoppingCart;
import cinema.shop.model.User;

public interface ShoppingCartDao {
    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    ShoppingCart update(ShoppingCart shoppingCart);
}

package cinema.shop.controller;

import cinema.shop.dto.response.OrderResponseDto;
import cinema.shop.model.Order;
import cinema.shop.model.ShoppingCart;
import cinema.shop.model.User;
import cinema.shop.service.OrderService;
import cinema.shop.service.ShoppingCartService;
import cinema.shop.service.UserService;
import cinema.shop.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper;

    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService,
                           ResponseDtoMapper<OrderResponseDto, Order>
                                   orderResponseDtoMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderResponseDtoMapper = orderResponseDtoMapper;
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(Authentication auth) {
        String email = auth.getName();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found"));
        ShoppingCart cart = shoppingCartService.getByUser(user);
        return orderResponseDtoMapper.mapToDto(orderService.completeOrder(cart));
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication auth) {
        String email = auth.getName();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found"));
        return orderService.getOrderHistory(user)
                .stream()
                .map(orderResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}

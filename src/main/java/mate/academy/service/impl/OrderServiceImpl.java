package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.service.OrderService;
import mate.academy.validator.order.OrderValidator;
import mate.academy.validator.shoppingcart.ShoppingCartValidator;
import mate.academy.validator.user.UserValidator;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartValidator shoppingCartValidator;
    @Inject
    private UserValidator userValidator;
    @Inject
    private OrderValidator orderValidator;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        shoppingCartValidator.isShoppingCart(shoppingCart);
        Order order = new Order(shoppingCart.getTickets(), LocalDateTime.now(),
                shoppingCart.getUser());
        shoppingCart.setTickets(Collections.emptyList());
        orderValidator.isOrderValid(order);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        userValidator.isUserValid(user);
        return orderDao.getAllOrdersByUser(user);
    }
}

package mate.academy.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import mate.academy.dao.ShoppingCartDao;
import mate.academy.dao.TicketDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.MovieSession;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Ticket;
import mate.academy.model.User;
import mate.academy.service.ShoppingCartService;
import mate.academy.validator.movie.session.MovieSessionValidator;
import mate.academy.validator.shoppingcart.ShoppingCartValidator;
import mate.academy.validator.user.UserValidator;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;
    @Inject
    private ShoppingCartValidator shoppingCartValidator;
    @Inject
    private UserValidator userValidator;
    @Inject
    private MovieSessionValidator movieSessionValidator;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        movieSessionValidator.isMovieSessionValid(movieSession);
        userValidator.isUserValid(user);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cannot find shopping cart by user: " + user));
        Ticket ticket = new Ticket(movieSession, user);
        ticketDao.add(ticket);
        List<Ticket> tickets = shoppingCart.getTickets();
        tickets.add(ticket);
        shoppingCart.setTickets(tickets);
        shoppingCartValidator.isShoppingCart(shoppingCart);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        userValidator.isUserValid(user);
        return shoppingCartDao.getByUser(user).orElseThrow(() ->
                new EntityNotFoundException("Cannot find shopping cart by user: " + user));
    }

    @Override
    public void registerNewShoppingCart(User user) {
        userValidator.isUserValid(user);
        ShoppingCart shoppingCart = new ShoppingCart(null, user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartValidator.isShoppingCart(shoppingCart);
        shoppingCart.setTickets(Collections.emptyList());
        shoppingCartDao.update(shoppingCart);
    }
}

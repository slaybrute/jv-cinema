package mate.academy.validator.shoppingcart.impl;

import mate.academy.exception.InvalidShoppingCartException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Ticket;
import mate.academy.validator.common.NullFieldValidator;
import mate.academy.validator.shoppingcart.ShoppingCartValidator;
import mate.academy.validator.ticket.TicketValidator;
import mate.academy.validator.user.UserValidator;

@Service
public class ShoppingCartValidatorImpl implements ShoppingCartValidator {
    @Inject
    private NullFieldValidator nullFieldValidator;
    @Inject
    private UserValidator userValidator;
    @Inject
    private TicketValidator ticketValidator;

    @Override
    public void isShoppingCart(ShoppingCart shoppingCart) {
        nullFieldValidator.isFieldNotNull(shoppingCart,
                new InvalidShoppingCartException("Shopping cart is null"));
        nullFieldValidator.isFieldNotNull(shoppingCart.getUser(),
                new InvalidShoppingCartException("User is null"));
        nullFieldValidator.isFieldNotNull(shoppingCart.getTickets(),
                new InvalidShoppingCartException("Tickets are null"));
        userValidator.isUserValid(shoppingCart.getUser());
        if (shoppingCart.getTickets().isEmpty()) {
            throw new InvalidShoppingCartException("Tickets are empty");
        }
        for (Ticket ticket : shoppingCart.getTickets()) {
            ticketValidator.isTicketValid(ticket);
        }
    }
}

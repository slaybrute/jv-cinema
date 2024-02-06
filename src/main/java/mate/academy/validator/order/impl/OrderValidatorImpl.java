package mate.academy.validator.order.impl;

import mate.academy.exception.InvalidOrderException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.Ticket;
import mate.academy.validator.common.NullFieldValidator;
import mate.academy.validator.order.OrderValidator;
import mate.academy.validator.ticket.TicketValidator;
import mate.academy.validator.user.UserValidator;

@Service
public class OrderValidatorImpl implements OrderValidator {
    @Inject
    private NullFieldValidator nullFieldValidator;
    @Inject
    private TicketValidator ticketValidator;
    @Inject
    private UserValidator userValidator;

    @Override
    public void isOrderValid(Order order) {
        nullFieldValidator.isFieldNotNull(order, new InvalidOrderException("Order is null"));
        nullFieldValidator.isFieldNotNull(order.getTickets(),
                new InvalidOrderException("Tickets are null"));
        nullFieldValidator.isFieldNotNull(order.getUser(),
                new InvalidOrderException("User is null"));
        userValidator.isUserValid(order.getUser());
        for (Ticket ticket : order.getTickets()) {
            ticketValidator.isTicketValid(ticket);
        }
    }
}

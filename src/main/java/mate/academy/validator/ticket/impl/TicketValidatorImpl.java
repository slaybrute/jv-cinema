package mate.academy.validator.ticket.impl;

import mate.academy.exception.InvalidTicketException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Ticket;
import mate.academy.validator.common.NullFieldValidator;
import mate.academy.validator.movie.session.MovieSessionValidator;
import mate.academy.validator.ticket.TicketValidator;

@Service
public class TicketValidatorImpl implements TicketValidator {
    @Inject
    private NullFieldValidator nullFieldValidator;

    @Inject
    private MovieSessionValidator movieSessionValidator;
    @Inject
    private TicketValidator ticketValidator;

    @Override
    public void isTicketValid(Ticket ticket) {
        nullFieldValidator.isFieldNotNull(ticket, new InvalidTicketException("Ticket is null"));
        movieSessionValidator.isMovieSessionValid(ticket.getMovieSession());
        ticketValidator.isTicketValid(ticket);
    }
}

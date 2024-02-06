package mate.academy.validator.movie.session.impl;

import java.time.LocalDate;
import mate.academy.exception.MovieSessionInitializationException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.MovieSession;
import mate.academy.validator.cinema.hall.CinemaHallValidator;
import mate.academy.validator.common.NullFieldValidator;
import mate.academy.validator.movie.MovieValidator;
import mate.academy.validator.movie.session.MovieSessionValidator;

@Service
public class MovieSessionValidatorImpl implements MovieSessionValidator {
    @Inject
    private NullFieldValidator nullFieldValidator;
    @Inject
    private MovieValidator movieValidator;
    @Inject
    private CinemaHallValidator cinemaHallValidator;

    @Override
    public void isMovieSessionValid(MovieSession movieSession) {
        nullFieldValidator.isFieldNotNull(movieSession,
                new MovieSessionInitializationException("Movie session is null"));
        isLocalDateValid(movieSession.getLocalDate());
        cinemaHallValidator.isCinemaHallValid(movieSession.getCinemaHall());
        movieValidator.isMovieValid(movieSession.getMovie());
    }

    @Override
    public void isLocalDateValid(LocalDate localDate) {
        nullFieldValidator.isFieldNotNull(localDate,
                new MovieSessionInitializationException("Local date is null"));
        if (localDate.isBefore(LocalDate.now())) {
            throw new MovieSessionInitializationException("Date is already passed");
        }
    }
}

package mate.academy.validator.movie.session;

import mate.academy.model.MovieSession;

public interface MovieSessionValidator extends LocalDateValidator {
    void isMovieSessionValid(MovieSession movieSession);
}

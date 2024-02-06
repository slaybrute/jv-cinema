package mate.academy.validator.movie;

import mate.academy.model.Movie;
import mate.academy.validator.DescriptionValidator;
import mate.academy.validator.TitleValidator;

public interface MovieValidator extends DescriptionValidator, TitleValidator {
    void isMovieValid(Movie movie);
}

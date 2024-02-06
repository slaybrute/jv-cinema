package mate.academy.validator.movie;

import mate.academy.model.Movie;
import mate.academy.validator.common.DescriptionValidator;

public interface MovieValidator extends DescriptionValidator, TitleValidator {
    void isMovieValid(Movie movie);
}

package mate.academy.validator.movie.impl;

import mate.academy.exception.MovieInitializationException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Movie;
import mate.academy.validator.common.NullFieldValidator;
import mate.academy.validator.movie.MovieValidator;

@Service
public class MovieValidatorImpl implements MovieValidator {
    private static final int INDEX_OF_FIRST_LETTER = 0;
    @Inject
    private NullFieldValidator nullFieldValidator;

    @Override
    public void isMovieValid(Movie movie) {
        nullFieldValidator.isFieldNotNull(movie,
                new MovieInitializationException("Movie is null"));
    }

    @Override
    public void isDescriptionValid(String description) {
        nullFieldValidator.isFieldNotNull(description,
                new MovieInitializationException("Description is null"));
        if (description.isEmpty()) {
            throw new MovieInitializationException("Description is empty");
        }
        if (Character.isUpperCase(description.charAt(INDEX_OF_FIRST_LETTER))) {
            throw new MovieInitializationException("Description should start with upper case");
        }
    }

    @Override
    public void isTitleValid(String title) {
        nullFieldValidator.isFieldNotNull(title,
                new MovieInitializationException("Title is null"));
        if (title.isEmpty()) {
            throw new MovieInitializationException("Title is empty");
        }
        if (Character.isUpperCase(title.charAt(INDEX_OF_FIRST_LETTER))) {
            throw new MovieInitializationException("Title should start with upper case");
        }
    }
}

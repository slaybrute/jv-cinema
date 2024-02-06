package mate.academy.validator.movie.impl;

import mate.academy.exception.InvalidMovieException;
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
                new InvalidMovieException("Movie is null"));
    }

    @Override
    public void isDescriptionValid(String description) {
        nullFieldValidator.isFieldNotNull(description,
                new InvalidMovieException("Description is null"));
        if (description.isEmpty()) {
            throw new InvalidMovieException("Description is empty");
        }
        if (Character.isUpperCase(description.charAt(INDEX_OF_FIRST_LETTER))) {
            throw new InvalidMovieException("Description should start with upper case");
        }
    }

    @Override
    public void isTitleValid(String title) {
        nullFieldValidator.isFieldNotNull(title,
                new InvalidMovieException("Title is null"));
        if (title.isEmpty()) {
            throw new InvalidMovieException("Title is empty");
        }
        if (Character.isUpperCase(title.charAt(INDEX_OF_FIRST_LETTER))) {
            throw new InvalidMovieException("Title should start with upper case");
        }
    }
}

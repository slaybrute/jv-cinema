package mate.academy.validator.cinema.hall.impl;

import mate.academy.exception.CinemaHallInitializationException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.CinemaHall;
import mate.academy.validator.cinema.hall.CinemaHallValidator;
import mate.academy.validator.common.NullFieldValidator;

@Service
public class CinemaHallValidatorImpl implements CinemaHallValidator {
    private static final int INDEX_OF_FIRST_LETTER = 0;
    @Inject
    private NullFieldValidator nullFieldValidator;

    @Override
    public void isCinemaHallValid(CinemaHall cinemaHall) {
        nullFieldValidator.isFieldNotNull(cinemaHall,
                new CinemaHallInitializationException("Cinema hall is null"));
        isCapacityValid(cinemaHall.getCapacity());
        isDescriptionValid(cinemaHall.getDescription());
    }

    @Override
    public void isDescriptionValid(String description) {
        nullFieldValidator.isFieldNotNull(description,
                new CinemaHallInitializationException("Description is null"));
        if (description.isEmpty()) {
            throw new CinemaHallInitializationException("Description is empty");
        }
        if (Character.isUpperCase(description.charAt(INDEX_OF_FIRST_LETTER))) {
            throw new CinemaHallInitializationException("Description should start with upper case");
        }
    }

    @Override
    public void isCapacityValid(int capacity) {
        if (capacity < 1) {
            throw new CinemaHallInitializationException("Capacity can't be less than 1");
        }
    }
}

package mate.academy.validator.cinema.hall;

import mate.academy.model.CinemaHall;
import mate.academy.validator.DescriptionValidator;

public interface CinemaHallValidator extends DescriptionValidator, CapacityValidator {
    void isCinemaHallValid(CinemaHall cinemaHall);
}

package mate.academy.validator.cinema.hall;

import mate.academy.model.CinemaHall;
import mate.academy.validator.common.DescriptionValidator;

public interface CinemaHallValidator extends DescriptionValidator, CapacityValidator {
    void isCinemaHallValid(CinemaHall cinemaHall);
}

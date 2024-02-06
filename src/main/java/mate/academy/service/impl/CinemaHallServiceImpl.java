package mate.academy.service.impl;

import java.util.List;
import mate.academy.dao.CinemaHallDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.CinemaHall;
import mate.academy.service.CinemaHallService;
import mate.academy.validator.cinema.hall.CinemaHallValidator;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallValidator cinemaHallValidator;
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        cinemaHallValidator.isCinemaHallValid(cinemaHall);
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public CinemaHall get(Long id) {
        return cinemaHallDao.get(id).orElseThrow(() ->
                new DataProcessingException("Can't find cinema hall by id: " + id));
    }

    @Override
    public List<CinemaHall> getAll() {
        List<CinemaHall> cinemaHalls = cinemaHallDao.getAll();
        if (cinemaHalls.isEmpty()) {
            throw new DataProcessingException("Can't find any cinema hall");
        }
        return cinemaHalls;
    }
}

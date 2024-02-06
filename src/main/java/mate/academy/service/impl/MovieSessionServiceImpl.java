package mate.academy.service.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.dao.MovieSessionDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.MovieSession;
import mate.academy.service.MovieSessionService;
import mate.academy.validator.movie.session.MovieSessionValidator;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;
    @Inject
    private MovieSessionValidator movieSessionValidator;

    @Override
    public MovieSession add(MovieSession movieSession) {
        movieSessionValidator.isMovieSessionValid(movieSession);
        return movieSessionDao.add(movieSession);
    }

    @Override
    public MovieSession get(Long id) {
        return movieSessionDao.get(id).orElseThrow(() ->
                new DataProcessingException("Can't find movie session by id"));
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        List<MovieSession> movieSessions = movieSessionDao.findAvailableSessions(movieId, date);
        if (movieSessions.isEmpty()) {
            throw new DataProcessingException("Can't find any movie session");
        }
        return movieSessions;
    }
}

package mate.academy.service.impl;

import java.util.List;
import mate.academy.dao.MovieDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Inject;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;
import mate.academy.validator.movie.MovieValidator;

public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieValidator movieValidator;
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        movieValidator.isMovieValid(movie);
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() ->
                new DataProcessingException("Cannot get movie by id " + id));
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = movieDao.getAll();
        if (movies.isEmpty()) {
            throw new DataProcessingException("Can't find any movie");
        }
        return movies;
    }
}

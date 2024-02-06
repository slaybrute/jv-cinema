package mate.academy.exception;

public class InvalidMovieSessionException extends RuntimeException {
    public InvalidMovieSessionException(String message) {
        super(message);
    }
}

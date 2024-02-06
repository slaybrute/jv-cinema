package mate.academy.exception;

public class InvalidMovieException extends RuntimeException {
    public InvalidMovieException(String message) {
        super(message);
    }
}

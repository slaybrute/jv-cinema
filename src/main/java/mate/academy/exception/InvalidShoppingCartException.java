package mate.academy.exception;

public class InvalidShoppingCartException extends RuntimeException {
    public InvalidShoppingCartException(String message) {
        super(message);
    }
}

package mate.academy.validator.user;

public interface SaltValidator {
    void isSaltValid(byte[] salts);
}

package mate.academy.validator.user;

import mate.academy.model.User;
import mate.academy.validator.common.EmailValidator;
import mate.academy.validator.common.PasswordValidator;

public interface UserValidator extends EmailValidator, PasswordValidator, SaltValidator {
    void isUserValid(User user);
}

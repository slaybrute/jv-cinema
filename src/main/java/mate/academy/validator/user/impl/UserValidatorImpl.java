package mate.academy.validator.user.impl;

import mate.academy.exception.InvalidUserException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.User;
import mate.academy.validator.common.NullFieldValidator;
import mate.academy.validator.user.UserValidator;

@Service
public class UserValidatorImpl implements UserValidator {
    @Inject
    private NullFieldValidator nullFieldValidator;

    @Override
    public void isUserValid(User user) {
        nullFieldValidator.isFieldNotNull(user,
                new InvalidUserException("User is null"));
        isEmailValid(user.getEmail());
        isPasswordValid(user.getPassword());
        isSaltValid(user.getSalts());
    }

    @Override
    public void isEmailValid(String email) {
        nullFieldValidator.isFieldNotNull(email,
                new InvalidUserException("Email is null"));
    }

    @Override
    public void isPasswordValid(String password) {
        nullFieldValidator.isFieldNotNull(password,
                new InvalidUserException("Password is null"));
    }

    @Override
    public void isSaltValid(byte[] salts) {
        nullFieldValidator.isFieldNotNull(salts,
                new InvalidUserException("Salts are null"));
    }
}

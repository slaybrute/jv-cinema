package mate.academy.validator.authentication.registration.impl;

import mate.academy.exception.DataProcessingException;
import mate.academy.exception.RegistrationException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.User;
import mate.academy.service.UserService;
import mate.academy.validator.authentication.registration.RegistrationValidator;
import mate.academy.validator.common.NullFieldValidator;

@Service
public class RegistrationValidatorImpl implements RegistrationValidator {
    @Inject
    private UserService userService;
    @Inject
    private NullFieldValidator nullFieldValidator;

    @Override
    public void isEmailValid(String email) {
        nullFieldValidator.isFieldNotNull(email,
                new RegistrationException("Email is null"));
        try {
            User user = userService.get(email);
        } catch (DataProcessingException e) {
            return;
        }
        throw new RegistrationException("User with such email is already registered. "
                + "Email: " + email);
    }

    @Override
    public void isPasswordValid(String password) {
        nullFieldValidator.isFieldNotNull(password,
                new RegistrationException("Password is null"));
        if (password.length() < 8) {
            throw new RegistrationException("Password lengths should be at least 8 characters");
        }
    }
}

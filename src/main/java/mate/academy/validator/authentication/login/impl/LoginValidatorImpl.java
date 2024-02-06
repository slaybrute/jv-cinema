package mate.academy.validator.authentication.login.impl;

import mate.academy.exception.DataProcessingException;
import mate.academy.exception.LoginException;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.User;
import mate.academy.service.UserService;
import mate.academy.util.HashUtil;
import mate.academy.validator.authentication.login.LoginValidator;
import mate.academy.validator.common.NullFieldValidator;

@Service
public class LoginValidatorImpl implements LoginValidator {
    @Inject
    private NullFieldValidator nullFieldValidator;
    @Inject
    private UserService userService;

    @Override
    public void isLoginValid(String email, String password) {
        nullFieldValidator.isFieldNotNull(email, new LoginException("Email is null"));
        nullFieldValidator.isFieldNotNull(password, new LoginException("Password is null"));
        User user;
        try {
            user = userService.get(email);
        } catch (DataProcessingException e) {
            throw new LoginException("Invalid email");
        }
        if (!user.getPassword().equals(HashUtil.hashPassword(password, user.getSalts()))) {
            throw new LoginException("Incorrect password: " + password);
        }
    }
}

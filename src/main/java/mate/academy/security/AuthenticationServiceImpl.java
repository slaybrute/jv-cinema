package mate.academy.security;

import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.User;
import mate.academy.service.UserService;
import mate.academy.util.HashUtil;
import mate.academy.validator.authentication.login.LoginValidator;
import mate.academy.validator.authentication.registration.RegistrationValidator;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private LoginValidator loginValidator;
    @Inject
    private RegistrationValidator registrationValidator;
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) {
        loginValidator.isLoginValid(email, password);
        return userService.get(email);
    }

    @Override
    public User register(String email, String password) {
        registrationValidator.isEmailValid(email);
        registrationValidator.isPasswordValid(password);
        User user = new User(email);
        user.setSalts(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(password, user.getSalts()));
        return userService.add(user);
    }
}

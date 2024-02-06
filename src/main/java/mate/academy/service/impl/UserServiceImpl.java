package mate.academy.service.impl;

import jakarta.persistence.EntityNotFoundException;
import mate.academy.dao.UserDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.User;
import mate.academy.service.UserService;
import mate.academy.validator.user.UserValidator;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;
    @Inject
    private UserValidator userValidator;

    @Override
    public User add(User user) {
        userValidator.isUserValid(user);
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow(() ->
                new EntityNotFoundException("Cannot find user by id: " + id));
    }

    @Override
    public User get(String email) {
        userValidator.isEmailValid(email);
        return userDao.get(email).orElseThrow(() ->
                new EntityNotFoundException("Cannot find user by email: " + email));
    }
}

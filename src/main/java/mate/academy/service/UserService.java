package mate.academy.service;

import mate.academy.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User get(String email);
}

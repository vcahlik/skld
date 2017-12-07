package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(Long id);

    Optional<User> findByPin(String pin);

    User create(User user);
}

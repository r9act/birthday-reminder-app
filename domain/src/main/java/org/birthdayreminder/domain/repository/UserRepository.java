package org.birthdayreminder.domain.repository;

import org.birthdayreminder.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    void updateUser(User user);

    Boolean userExists(User user);

    Long saveNewUser(User user);

    Long saveUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByForeignId(Long foreignId);
}

package org.birthdayreminder.domain.repository.impl;

import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.UserRepository;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    List<User> list = new ArrayList<>();

    private static Long counterId = 1L;

    @Override
    public void updateUser(User user) {
        user.setIsReminderActive(!user.getIsReminderActive());
    }

    @Override
    public Boolean userExists(User user) {
        return list.stream().anyMatch(u -> u.getForeignId().equals(user.getForeignId()));
    }

    @Override
    public Long saveNewUser(User user) {

        if (!userExists(user)) {
            user.setId(counterId);
            counterId++;
            list.add(user);
            return user.getId();
        }
        return getUserByForeignId(user.getForeignId()).orElseThrow(NullPointerException::new).getId();
    }

    @Override public Long saveUser(User user) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return list.stream().filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByForeignId(Long foreignId) {
        return list.stream().filter(u -> u.getForeignId().equals(foreignId))
                .findFirst();
    }
}

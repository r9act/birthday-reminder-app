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
        return list.stream().anyMatch(u -> u.getChatId().equals(user.getChatId()));
    }

    @Override
    public Long saveNewUser(User user) {

        if (!userExists(user)) {
            user.setId(counterId);
            counterId++;
            list.add(user);
            return user.getId();
        }
        return getUserByChatId(user.getChatId()).orElseThrow(NullPointerException::new).getId();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return list.stream().filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByChatId(Long chatId) {
        return list.stream().filter(u -> u.getChatId().equals(chatId))
                .findFirst();
    }
}

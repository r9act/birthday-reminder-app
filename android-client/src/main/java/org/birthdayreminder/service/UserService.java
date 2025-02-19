package org.birthdayreminder.service;

import org.birthdayreminder.domain.model.User;

import java.util.Optional;

public interface UserService {
	boolean updateUser(Long chatId, User user);

	Long saveNewUser(User user);

	Optional<User> getUserByChatId(Long chatId);
}

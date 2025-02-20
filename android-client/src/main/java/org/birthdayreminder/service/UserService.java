package org.birthdayreminder.service;

import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface UserService {
	boolean updateUser(UserDto userDto);

	Long saveNewUser(UserDto userDto);

	Optional<UserDto> getUserByChatId(Long chatId);
}

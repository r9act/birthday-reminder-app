package org.birthdayreminder.service;

import org.birthdayreminder.app.UserDto;

import java.util.Optional;
public interface UserService {
	boolean updateUserInfo(UserDto userDto);

	Long createUser(UserDto userDto);

	Optional<UserDto> getUserByForeignId(Long foreignId);
}

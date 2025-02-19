package org.birthdayreminder.service;

import org.birthdayreminder.app.mapper.UserMapper;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author a.mishkin
 */
@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override public boolean updateUser(Long chatId, User user) {
		Optional<User> existingUserOpt = userRepository.getUserById(chatId);
		if (existingUserOpt.isEmpty()) {
			return false;
		}

		User existingUser = existingUserOpt.get();
		userMapper.updateUserFromDto(userMapper.toDto(user), existingUser);

		userRepository.updateUser(existingUser);
		return true;
	}

	@Override public Long saveNewUser(User user) {
		return userRepository.saveNewUser(user);
	}


	@Override public Optional<User> getUserByChatId(Long chatId) {
		Optional<User> user = userRepository.getUserByChatId(chatId);
		return user;
	}
}

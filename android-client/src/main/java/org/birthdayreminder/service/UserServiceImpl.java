package org.birthdayreminder.service;

import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.app.mapper.UserMapper;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author a.mishkin
 */
@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override public boolean updateUser(UserDto userDto) {
		Optional<User> existingUserOpt = userRepository.getUserById(userDto.getAndroidChatId());
		if (existingUserOpt.isEmpty()) {
			return false;
		}
		User existingUser = existingUserOpt.get();
		userMapper.updateUserFromDto(userDto, existingUser);
		userRepository.updateUser(existingUser);
		return true;
	}

	@Override public Long saveNewUser(UserDto userDto) {
		User user = userMapper.toModel(userDto);
		Long userId = userRepository.saveNewUser(user);
		logger.info("New user saved: id={}, chatId={}, name={}", userId, user.getChatId(), user.getName());
		return userId;
	}


	@Override public Optional<UserDto> getUserByChatId(Long chatId) {
		Optional<User> userOptional = userRepository.getUserByChatId(chatId);
		userOptional.ifPresent(user ->
				logger.info("User found: {}", user)
		);
		return userOptional.map(userMapper::toDto);
	}
}

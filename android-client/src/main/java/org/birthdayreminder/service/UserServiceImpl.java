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

	@Override
	public Long createUser(UserDto userDto) {
		if (userRepository.getUserByForeignId(userDto.getForeignId()).isPresent()) {
			logger.error("User exists: foreignId={}, name={}", userDto.getForeignId(), userDto.getName());
			throw  new RuntimeException("User " + userDto.getForeignId() + " exists");
		}
		User user = userMapper.toModel(userDto);
		Long userId = userRepository.saveUser(user);
		logger.info("New user saved: id={}, foreignId={}, name={}", userId, user.getForeignId(), user.getName());
		return userId;
	}

	@Override
	public Optional<UserDto> getUserByForeignId(Long foreignId) {
		Optional<User> userOptional = userRepository.getUserByForeignId(foreignId);
		userOptional.ifPresentOrElse(user ->
				logger.info("User found: {}", user), () ->  { throw new RuntimeException("User not found: " + foreignId);});
		return userOptional.map(userMapper::toDto);
	}

	@Override
	public boolean updateUserInfo(UserDto userDto) {
		Optional<User> existingUserOpt = userRepository.getUserByForeignId(userDto.getForeignId());
		User existingUser = existingUserOpt.orElseThrow(() ->
				new RuntimeException("User not found: " + userDto.getForeignId())
		);
		// объект user передается по ссылке, и MapStruct обновляет его на месте.
		userMapper.updateUserFromDto(userDto, existingUser);
		userRepository.saveUser(existingUser);
		return true;
	}
}

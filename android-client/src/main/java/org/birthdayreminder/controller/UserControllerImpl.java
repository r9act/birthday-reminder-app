package org.birthdayreminder.controller;

import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.app.mapper.UserMapper;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
	private final UserService userService;

	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		logger.info("[API - USER_CONTROLLER - GET_USER_BY_ID]");
		return userService.getUserByChatId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
		logger.info("[API - USER_CONTROLLER - CREATE_USER]");
		Long userId = userService.saveNewUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userId);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Boolean> updateUser(@RequestBody UserDto userDto) {
		logger.info("[API - USER_CONTROLLER - UPDATE_USER]");
		//user.setId(id); - порешать PK в mapStruct
		return ResponseEntity.ok(userService.updateUser(userDto));
	}
}
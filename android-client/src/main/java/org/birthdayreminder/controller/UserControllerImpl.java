package org.birthdayreminder.controller;

import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserControllerImpl {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
	private final UserService userService;

	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
		logger.info("[API - USER_CONTROLLER - CREATE_USER]");
		Long userId = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userId);
	}

	@GetMapping("/{foreignId}")
	public ResponseEntity<UserDto> getUserByForeignId(@PathVariable Long foreignId) {
		logger.info("[API - USER_CONTROLLER - GET_USER_BY_ID]");
		return userService.getUserByForeignId(foreignId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Boolean> updateUser(@RequestBody UserDto userDto) {
		logger.info("[API - USER_CONTROLLER - UPDATE_USER]");
		return ResponseEntity.ok(userService.updateUserInfo(userDto));
	}
}
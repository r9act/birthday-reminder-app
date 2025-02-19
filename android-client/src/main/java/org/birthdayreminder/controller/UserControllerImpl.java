package org.birthdayreminder.controller;

import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.app.mapper.UserMapper;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.service.UserService;
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

	private final UserService userService;
	private final UserMapper userMapper;

	public UserControllerImpl(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		return userService.getUserByChatId(id)
				.map(userMapper::toDto)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
		User user = userMapper.toModel(userDto);
		Long userId = userService.saveNewUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userId);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Boolean> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		User user = userMapper.toModel(userDto);
		//user.setId(id); - порешать PK в mapStruck
		return ResponseEntity.ok(userService.updateUser(id, user));
	}
}
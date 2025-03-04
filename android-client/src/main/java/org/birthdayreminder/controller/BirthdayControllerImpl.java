package org.birthdayreminder.controller;

import org.birthdayreminder.app.PersonBirthdayDto;
import org.birthdayreminder.service.PersonBirthdayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author a.mishkin
 */
@RestController
@RequestMapping("/api/birthdays")
public class BirthdayControllerImpl {

	private final PersonBirthdayService personBirthdayService;
	private final Logger logger = LoggerFactory.getLogger(BirthdayControllerImpl.class);

	public BirthdayControllerImpl(PersonBirthdayService personBirthdayService) {
		this.personBirthdayService = personBirthdayService;
	}

	@PostMapping("/{foreignId}")
	public ResponseEntity<Void> saveUserBirthdays(@PathVariable Long foreignId, @RequestBody List<PersonBirthdayDto> birthdays) {
		logger.info("[API - BIRTHDAY_CONTROLLER - SAVE_BIRTHDAYS]");
		personBirthdayService.processBirthdayList(birthdays, foreignId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{foreignId}")
	public ResponseEntity<List<PersonBirthdayDto>> getUserBirthdays(@PathVariable Long foreignId) {
		logger.info("[API - BIRTHDAY_CONTROLLER - GET_USER_BIRTHDAYS]");
		return ResponseEntity.ok(personBirthdayService.prepareBirthdayList(foreignId));
	}
}

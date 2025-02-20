package org.birthdayreminder.controller;

import org.birthdayreminder.app.PersonBirthdayDto;
import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.service.PersonBirthdayService;
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

	public BirthdayControllerImpl(PersonBirthdayService personBirthdayService) {
		this.personBirthdayService = personBirthdayService;
	}

	@PostMapping
	public ResponseEntity<Long> saveBirthdays(@RequestBody List<PersonBirthdayDto> personBirthdayDtoList) {
		personBirthdayService.processBirthdayList(personBirthdayDtoList);
		return ResponseEntity.status(HttpStatus.CREATED).body((long) personBirthdayDtoList.size());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<List<PersonBirthdayDto>> getAllBirthdays(@PathVariable Long userId) {
		return personBirthdayService.prepareBirthdayList(userId);
	}
}

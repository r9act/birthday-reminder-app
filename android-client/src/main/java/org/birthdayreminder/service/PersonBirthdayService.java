package org.birthdayreminder.service;

import org.birthdayreminder.app.PersonBirthdayDto;
import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonBirthdayService {
	void processBirthdayList(List<PersonBirthdayDto> personBirthdayList);

	ResponseEntity<List<PersonBirthdayDto>> prepareBirthdayList(Long userId);
}

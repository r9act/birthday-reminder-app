package org.birthdayreminder.service;

import org.birthdayreminder.app.PersonBirthdayDto;


import java.util.List;

public interface PersonBirthdayService {
	void processBirthdayList(List<PersonBirthdayDto> personBirthdayList, Long foreignId);

	List<PersonBirthdayDto> prepareBirthdayList(Long foreignId);
}

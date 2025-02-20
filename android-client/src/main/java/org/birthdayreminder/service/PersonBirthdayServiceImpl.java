package org.birthdayreminder.service;

import org.birthdayreminder.app.PersonBirthdayDto;
import org.birthdayreminder.app.UserDto;
import org.birthdayreminder.app.mapper.PersonBirthdayMapper;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a.mishkin
 */
@Service
public class PersonBirthdayServiceImpl implements PersonBirthdayService {

	private final PersonBirthdayRepository personBirthdayRepository;

	private final PersonBirthdayMapper personBirthdayMapper;

	public PersonBirthdayServiceImpl(PersonBirthdayRepository personBirthdayRepository,
			PersonBirthdayMapper personBirthdayMapper) {
		this.personBirthdayRepository = personBirthdayRepository;
		this.personBirthdayMapper = personBirthdayMapper;
	}

	@Override public void processBirthdayList(List<PersonBirthdayDto> personBirthdayList) {
		personBirthdayRepository.saveAll(personBirthdayList.stream().map(personBirthdayMapper::toModel).collect(
				Collectors.toList()));
	}

	@Override
	public ResponseEntity<List<PersonBirthdayDto>> prepareBirthdayList(Long userId) {
		return ResponseEntity.ok(
				personBirthdayRepository.getAllByUserId(userId).stream()
						.map(personBirthdayMapper::toDto)
						.collect(Collectors.toList())
		);
	}
}

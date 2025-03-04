package org.birthdayreminder.service;

import org.birthdayreminder.app.PersonBirthdayDto;
import org.birthdayreminder.app.mapper.PersonBirthdayMapper;
import org.birthdayreminder.app.mapper.UserMapper;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.birthdayreminder.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a.mishkin
 */
@Service
public class PersonBirthdayServiceImpl implements PersonBirthdayService {

	private final PersonBirthdayRepository personBirthdayRepository;
	private final UserMapper userMapper;

	private final UserRepository userRepository;
	private final PersonBirthdayMapper personBirthdayMapper;

	public PersonBirthdayServiceImpl(PersonBirthdayRepository personBirthdayRepository, UserMapper userMapper, UserRepository userRepository,
			PersonBirthdayMapper personBirthdayMapper) {
		this.personBirthdayRepository = personBirthdayRepository;
		this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.personBirthdayMapper = personBirthdayMapper;
	}

	@Override
	public void processBirthdayList(List<PersonBirthdayDto> personBirthdayList, Long foreignId) {
		User user = userRepository.getUserByForeignId(foreignId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		List<PersonBirthday> personBirthdays = personBirthdayList.stream()
				.peek(pb -> pb.setOwner(userMapper.toDto(user)))
				.map(personBirthdayMapper::toModel)
				.collect(Collectors.toList());
		personBirthdayRepository.saveAll(personBirthdays);
	}

	@Override
	public List<PersonBirthdayDto> prepareBirthdayList(Long foreignId) {
		User user = userRepository.getUserByForeignId(foreignId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return personBirthdayRepository.getAllByUserId(user.getId()).stream()
						.map(personBirthdayMapper::toDto)
						.collect(Collectors.toList());
	}
}

package org.birthdayreminder.domain.service.impl;

import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.birthdayreminder.domain.repository.UserRepository;
import org.birthdayreminder.domain.service.UserBirthdayService;

import java.util.List;

public class UserBirthdayServiceImpl implements UserBirthdayService {
    private final UserRepository userRepository;
    private final PersonBirthdayRepository personBirthdayRepository;

    public UserBirthdayServiceImpl(UserRepository userRepository, PersonBirthdayRepository personBirthdayRepository) {
        this.userRepository = userRepository;
        this.personBirthdayRepository = personBirthdayRepository;
    }

    @Override
    public List<PersonBirthday> linkBirthdayToUser(List<PersonBirthday> list, Long id) {

        var user = userRepository.getUserById(id).orElseThrow(NullPointerException::new) ;

        var modifiedList =
                list.stream()
                        .map(personBirthday -> personBirthday.setUser(user))
                        .toList();
        personBirthdayRepository.saveAll(modifiedList);

        return modifiedList;
    }
}

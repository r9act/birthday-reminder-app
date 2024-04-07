package org.birthdayreminder.domain.service;

import org.birthdayreminder.domain.model.PersonBirthday;

import java.util.List;

/**
 * связываем parsed ДРы с user - хозяином
 */
public interface UserBirthdayService {
    List<PersonBirthday> linkBirthdayToUser(List<PersonBirthday> list, Long id);
}

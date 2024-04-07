package org.birthdayreminder.domain.repository;

import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.model.DatePeriod;
import org.birthdayreminder.domain.model.User;

import java.util.List;

public interface PersonBirthdayRepository {

    Integer saveAll(List<PersonBirthday> personBirthdays);

    List<PersonBirthday> getAllByUserId(Long id);

    List<PersonBirthday> getAllBirthdaysIn(DatePeriod datePeriod);

    List<PersonBirthday> findByDatePeriodAndUserId(DatePeriod datePeriod, Long id);

}

package org.birthdayreminder.domain.repository.impl;

import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.model.DatePeriod;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents "InMemory" repository implementation
 */
public class InMemoryPersonBirthdayRepository implements PersonBirthdayRepository {

    List<PersonBirthday> list = new ArrayList<>();

    /**
     * принимает список объектов с установленными chatId (@UserPersonService)
     * сохраняет и считает кол-во элементов
     */
    @Override
    public Integer saveAll(List<PersonBirthday> personBirthdays) {
        list.addAll(personBirthdays);
        return list.size();
    }

    /**
     * вывод списка ДР конкретного юзера
     */
    @Override
    public List<PersonBirthday> getAllByUserId(Long id) {
        return list.stream()
                .filter(personBirthday -> personBirthday.user().getId().equals(id))
                .toList();
    }

    /**
     * Part 1 of Scheduled method
     */
    @Override
    public List<PersonBirthday> getAllBirthdaysIn(DatePeriod datePeriod) {
        return list.stream()
                .filter(personBirthday -> personBirthday.date().getDayOfYear() > datePeriod.from().getDayOfYear()
                        && personBirthday.date().getDayOfYear() < datePeriod.to().getDayOfYear())
                .toList();
    }

    /**
     * Переделанный метод под dayOfYear (если год рождения неизвестен)
     */
    @Override
    public List<PersonBirthday> findByDatePeriodAndUserId(DatePeriod datePeriod, Long id) {

        return list.stream()
                .filter(personBirthday -> personBirthday.user().getId().equals(id))
                .filter(personBirthday -> personBirthday.date().getDayOfYear() > datePeriod.from().getDayOfYear()
                        && personBirthday.date().getDayOfYear() < datePeriod.to().getDayOfYear())
                .toList();
    }
}

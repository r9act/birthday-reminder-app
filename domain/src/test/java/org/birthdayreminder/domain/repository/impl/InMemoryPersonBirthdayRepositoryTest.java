package org.birthdayreminder.domain.repository.impl;

import org.birthdayreminder.domain.model.DatePeriod;
import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.model.User;
import org.birthdayreminder.domain.repository.PersonBirthdayRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class InMemoryPersonBirthdayRepositoryTest {

    private final PersonBirthdayRepository repository = new InMemoryPersonBirthdayRepository();

    private final User user = new User(1L,1L, "Name", false);

    private final PersonBirthday personBirthday = new PersonBirthday(1L, "A", "B", LocalDate.now(),  new User(1L,1L, "Name", false));

    private final DatePeriod datePeriod = new DatePeriod(LocalDate.MIN, LocalDate.MAX);



    @Test
    public void shouldSaveBirthdays() {
        var count = repository.saveAll(Collections.emptyList());

        var result = repository.findByDatePeriodAndUserId(datePeriod, user.getId());

        Assertions.assertEquals(0, count);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void findByDatePeriod() {
        var count = repository.saveAll(new ArrayList<>(Collections.singleton(personBirthday)));

        List<PersonBirthday> actual = repository.getAllByUserId(user.getId());
        Assertions.assertEquals(count, actual.size());
    }
}
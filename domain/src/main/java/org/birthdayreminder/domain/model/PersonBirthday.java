package org.birthdayreminder.domain.model;

import lombok.NonNull;

import java.time.LocalDate;

public record PersonBirthday(
        Long id,
        @NonNull String name,
        String surname,
        @NonNull LocalDate date,
        User user) {

    public PersonBirthday(@NonNull String name, String surname, @NonNull LocalDate date) {
        this(null, name, surname, date, null);
    }

    public PersonBirthday setUser(User user) {
        return new PersonBirthday(id, name, surname, date, user);
    }
}

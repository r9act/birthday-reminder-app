package org.birthdayreminder.domain.model;

import lombok.NonNull;

import java.time.LocalDate;

public record DatePeriod(@NonNull LocalDate from, @NonNull LocalDate to) {
    void init() {
        if (from.isAfter(to) || from.isEqual(to))
            throw new IllegalArgumentException("`From` value cannot be bigger or equal than `to` value");
    }
}

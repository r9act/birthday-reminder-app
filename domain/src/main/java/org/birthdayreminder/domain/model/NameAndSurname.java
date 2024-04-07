package org.birthdayreminder.domain.model;

import lombok.NonNull;

public record NameAndSurname(@NonNull String name, String surname) {
}

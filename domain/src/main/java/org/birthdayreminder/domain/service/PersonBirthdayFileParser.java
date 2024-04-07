package org.birthdayreminder.domain.service;

import org.birthdayreminder.domain.model.PersonBirthday;

import java.io.InputStream;
import java.util.List;

public interface PersonBirthdayFileParser {

    List<PersonBirthday> parse(InputStream inputStream);
}

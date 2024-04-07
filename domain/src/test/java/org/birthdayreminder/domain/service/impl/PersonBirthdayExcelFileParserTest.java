package org.birthdayreminder.domain.service.impl;

import org.birthdayreminder.domain.model.PersonBirthday;
import org.birthdayreminder.domain.service.PersonBirthdayFileParser;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonBirthdayExcelFileParserTest {

    private final PersonBirthdayFileParser parser = new PersonBirthdayExcelFileParser();
    private final String CORRECT_FILE_LINK = "xlsx/correct_file.xlsx";
    private final String EMPTY_FILE_LINK = "xlsx/empty_file.xlsx";
    private final String NO_FILE_LINK = "xlsx/no_such_file.xlsx";

    @Test
    public void shouldParseExcelFile() {
        var file = load(CORRECT_FILE_LINK);
        var result = parser.parse(file);

        assertEquals(5, result.size());
    }

    @Test
    public void shouldParseExcelFileEmpty() {
        var file = load(EMPTY_FILE_LINK);
        var result = parser.parse(file);

        assertEquals(0, result.size());
    }

    @Test
    public void shouldNotFindTheFile() {
        var file = load(NO_FILE_LINK);

        assertNull(file);
    }

    @Test
    public void shouldHaveValidResult() {
        PersonBirthday pb = new PersonBirthday("Person1", "Name Surname Nickname", LocalDate.of(2024, 10, 1));
        var file = load(CORRECT_FILE_LINK);
        var result = parser.parse(file);

        assertEquals(pb, result.get(0));
    }

    private InputStream load(String file) {
        try {
            var resource = getClass().getClassLoader().getResource(file);
            if (resource == null) return null;

            return resource.openStream();
        } catch (Exception ignored) {
            throw new IllegalArgumentException("No file " + file + " exists");
        }
    }
}

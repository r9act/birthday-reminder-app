package org.birthdayreminder.domain.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private Long foreignId;
    private String name;
    private Boolean isReminderActive;


    public User() {
    }

    public User(Long foreignId, String name, Boolean isReminderActive) {
        this.foreignId = foreignId;
        this.name = name;
        this.isReminderActive = isReminderActive;
    }

    public User(Long id, Long foreignId, String name, Boolean isReminderActive) {
        this.id = id;
        this.foreignId = foreignId;
        this.name = name;
        this.isReminderActive = isReminderActive;
    }
}

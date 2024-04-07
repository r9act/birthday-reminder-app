package org.birthdayreminder.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    private Long id;
    private Long chatId;
    private String name;
    private Boolean isReminderActive;


    public User() {
    }

    public User(Long chatId, String name, Boolean isReminderActive) {
        this.chatId = chatId;
        this.name = name;
        this.isReminderActive = isReminderActive;
    }

    public User(Long id, Long chatId, String name, Boolean isReminderActive) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
        this.isReminderActive = isReminderActive;
    }
}

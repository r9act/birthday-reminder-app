package org.birthdayreminder.app.repository.impl;

public class SQLConstants {
    public static final String SELECT_BY_CHAT_ID = "SELECT * FROM users WHERE chat_id = :chatId";
    public static final String UPDATE_REMINDER_STATUS_BY_CHAT_ID = "UPDATE users SET is_reminder_active = !is_reminder_active \" +\n" +
            "                \"WHERE chat_id = :chatId";
}

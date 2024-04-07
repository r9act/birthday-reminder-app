package org.birthdayreminder.client;

public record TelegramRestClientSettings(
        String telegramApiEndpoint,
        String privateToken,
        Integer timeOutSeconds
) {
}

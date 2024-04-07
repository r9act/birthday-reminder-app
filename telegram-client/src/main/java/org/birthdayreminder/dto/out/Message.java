package org.birthdayreminder.dto.out;

public class Message {
    private Long chatId;
    private String text;
    private KeyboardMarkup keyboardMarkup;

    public Message(Long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }
    public Message() {
    }

    public Message(Long chatId, String text, KeyboardMarkup keyboardMarkup) {
        this.chatId = chatId;
        this.text = text;
        this.keyboardMarkup = keyboardMarkup;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public KeyboardMarkup getKeyboardMarkup() {
        return keyboardMarkup;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setKeyboardMarkup(KeyboardMarkup keyboardMarkup) {
        this.keyboardMarkup = keyboardMarkup;
    }
}

package org.birthdayreminder.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InlineKeyboardMarkup extends KeyboardMarkup {

    @JsonProperty(value = "inline_keyboard")
    private List<List<InlineKeyboardButton>> keyboard;

    public InlineKeyboardMarkup(List<List<InlineKeyboardButton>> keyboard) {
        this.keyboard = keyboard;
    }

    public InlineKeyboardMarkup() {
    }

    public List<List<InlineKeyboardButton>> getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(List<List<InlineKeyboardButton>> keyboard) {
        this.keyboard = keyboard;
    }
}

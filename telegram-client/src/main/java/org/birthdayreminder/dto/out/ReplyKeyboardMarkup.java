package org.birthdayreminder.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReplyKeyboardMarkup extends KeyboardMarkup {

    @JsonProperty(value = "keyboard")
    private List<List<KeyboardButton>> keyboard;

    public ReplyKeyboardMarkup(List<List<KeyboardButton>> keyboard) {
        this.keyboard = keyboard;
    }

    public List<List<KeyboardButton>> getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(List<List<KeyboardButton>> keyboard) {
        this.keyboard = keyboard;
    }
}

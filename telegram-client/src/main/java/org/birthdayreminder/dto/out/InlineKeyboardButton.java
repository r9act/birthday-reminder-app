package org.birthdayreminder.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InlineKeyboardButton {

    @JsonProperty(value = "text")
    private String text;
    @JsonProperty(value = "callback_data")
    private String callbackData;

    public InlineKeyboardButton(String text, String callbackData) {
        this.text = text;
        this.callbackData = callbackData;
    }

    public InlineKeyboardButton(String text) {
        this.text = text;
    }

    public InlineKeyboardButton() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }
}

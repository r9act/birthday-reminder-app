package org.birthdayreminder.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyboardButton {
    @JsonProperty(value = "text")
    private String text;

    public KeyboardButton(String text) {
        this.text = text;
    }
}

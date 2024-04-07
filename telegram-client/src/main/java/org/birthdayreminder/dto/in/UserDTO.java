package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    @JsonProperty(value = "id")
    private Long chatId;
    @JsonProperty(value = "first_name")
    private String firstName;

    public Long getChatId() {
        return chatId;
    }

    public String getFirstName() {
        return firstName;
    }
}

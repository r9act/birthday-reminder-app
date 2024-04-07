package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackQuery {
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "from")
    private UserDTO user;
    @JsonProperty(value = "message")
    private MessageDTO message;
    @JsonProperty(value = "chat_instance")
    private String chatInstance;
    @JsonProperty(value = "data")
    private String data;

    public String getId() {
        return id;
    }

    public UserDTO getUser() {
        return user;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public String getChatInstance() {
        return chatInstance;
    }

    public String getData() {
        return data;
    }
}

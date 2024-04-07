package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO {

    @JsonProperty(value = "update_id")
    private Integer updateId;
    @JsonProperty(value = "message")
    private MessageDTO message;
    @JsonProperty(value = "callback_query")
    private CallbackQuery callbackQuery;

    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public MessageDTO getMessage() {
        return message;
    }
}

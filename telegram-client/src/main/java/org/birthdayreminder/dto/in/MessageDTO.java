package org.birthdayreminder.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.birthdayreminder.dto.out.InlineKeyboardMarkup;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO {

    @JsonProperty(value = "text")
    private String text;
    @JsonProperty(value = "document")
    private DocumentDTO document;
    @JsonProperty(value = "from")
    private UserDTO user;
    @JsonProperty(value = "reply_markup")
    private InlineKeyboardMarkup replyMarkup;


    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public String getText() {
        return text;
    }

    public UserDTO getUser() {
        return user;
    }

    public DocumentDTO getDocument() {
        return document;
    }
}

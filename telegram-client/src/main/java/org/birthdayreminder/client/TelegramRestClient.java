package org.birthdayreminder.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.birthdayreminder.dto.in.ResultDTO;
import org.birthdayreminder.dto.out.KeyboardMarkup;
import org.birthdayreminder.dto.out.Message;

import java.io.InputStream;
import java.util.List;

public interface TelegramRestClient {

    List<ResultDTO> getUpdates(Integer offset);

    List<ResultDTO> getUpdates();

    InputStream downloadFile(String fileId);

    void sendMessage(Message message);

    void sendMessage(Message message, KeyboardMarkup keyboardMarkup) throws JsonProcessingException;

}

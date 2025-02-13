package org.birthdayreminder.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.birthdayreminder.client.TelegramRestClient;

import org.birthdayreminder.dto.in.ResultDTO;
import org.birthdayreminder.dto.out.Message;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class UpdateHandler {

    private final TelegramRestClient client;
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    public UpdateHandler(TelegramRestClient client, MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler) {
        this.client = client;
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    public void proceedResultDto(ResultDTO result) throws JsonProcessingException {
        if (result.getMessage() != null && result.getMessage().getText()
                != null && !result.getMessage().getText().isEmpty()) {
            var msg = messageHandler.handleTextMessage(result);
            var keyboardMarkup = msg.getKeyboardMarkup();
            client.sendMessage(msg, keyboardMarkup);
        } else if (result.getMessage() != null && result.getMessage().getDocument() != null) {
            InputStream is = client.downloadFile(result.getMessage().getDocument().getFileId());
            client.sendMessage(messageHandler.handleDocumentMessage(is, result));
        } else if (result.getCallbackQuery() != null) {
            Message message = callbackQueryHandler.handleCallback(result);
            client.sendMessage(message);
        }
    }

    public ResultDTO getResultDTOFromUpdate() {
        var size = client.getUpdates().size();
        if (size > 0) {
            ResultDTO result = client.getUpdates().get(0);
            var offset = client.getUpdates().get(0).getUpdateId();
            client.getUpdates(offset + 1);
            return result;
        } else {
            return new ResultDTO();
        }
    }
}

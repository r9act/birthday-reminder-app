package org.birthdayreminder.app.handlers;

import org.birthdayreminder.dto.in.ResultDTO;
import org.birthdayreminder.dto.out.Message;

import java.io.InputStream;

public interface MessageHandler {

    Message handleTextMessage(ResultDTO result);

    Message handleDocumentMessage(InputStream is, ResultDTO result);
}

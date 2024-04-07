package org.birthdayreminder.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.birthdayreminder.client.TelegramRestClient;
import org.birthdayreminder.client.TelegramRestClientSettings;
import org.birthdayreminder.dto.in.ResultDTO;
import org.birthdayreminder.dto.in.ResultFileDTO;
import org.birthdayreminder.dto.in.UpdateDTO;
import org.birthdayreminder.dto.out.KeyboardMarkup;
import org.birthdayreminder.dto.out.Message;

import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import java.util.Collections;
import java.util.List;

import static org.birthdayreminder.client.RestMethods.*;

public class TelegramRestClientImpl implements TelegramRestClient {

    private final String telegramApiEndpoint;
    private final String privateToken;
    private final Integer timeOutSeconds;

    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public TelegramRestClientImpl(TelegramRestClientSettings settings) {
        this.telegramApiEndpoint = settings.telegramApiEndpoint();
        this.privateToken = settings.privateToken();
        this.timeOutSeconds = settings.timeOutSeconds();
    }

    @Override
    public List<ResultDTO> getUpdates() {
        return getUpdates(null);
    }

    @Override
    public List<ResultDTO> getUpdates(Integer offset) {

        String getUpdatesMethod;
        if (offset != null) {
            getUpdatesMethod = GETUPDATESOFFSET.getParamUpdate(offset, timeOutSeconds);
        } else {
            getUpdatesMethod = GETUPDATES.getPath() + "?timeout=" + timeOutSeconds;
        }
        HttpRequest build = HttpRequest.newBuilder()
                .uri(URI.create(telegramApiEndpoint + privateToken + getUpdatesMethod))
                .GET()
                .build();
        try {
            var response = httpClient.send(build, HttpResponse.BodyHandlers.ofString());
            
            UpdateDTO updateDTO = mapper.readValue(response.body(), UpdateDTO.class);

            return updateDTO.getResultDTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public InputStream downloadFile(String fileId) {

        HttpRequest requestFileDTO = HttpRequest.newBuilder()
                .uri(URI.create(telegramApiEndpoint + privateToken + GETFILE.getPath() + fileId))
                .GET()
                .build();
        try {
            var responseWithFileDto = httpClient.send(requestFileDTO, HttpResponse.BodyHandlers.ofString());
            ResultFileDTO resultFileDTO = mapper.readValue(responseWithFileDto.body(), ResultFileDTO.class);

            final String filePath = resultFileDTO.getFileDTO().getFilePath();

            HttpRequest requestFileDownload = HttpRequest.newBuilder()
                    .uri(URI.create(telegramApiEndpoint + "file/" + privateToken + "/" + filePath))
                    .GET()
                    .build();

            final var response = httpClient.send(requestFileDownload, HttpResponse.BodyHandlers.ofInputStream());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(Message message) {
        var chatId = message.getChatId();
        var text = message.getText();
        var cleanText = URLEncoder.encode(text, StandardCharsets.UTF_8);

        HttpRequest sendMessage = HttpRequest.newBuilder()
                .uri(URI.create(telegramApiEndpoint + privateToken + SENDMESSAGE.getParamPathSendMessage(chatId, cleanText)))
                .GET()
                .build();
        try {
            httpClient.send(sendMessage, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(Message message, KeyboardMarkup keyboardMarkup) throws JsonProcessingException {
        if (keyboardMarkup == null) {
            sendMessage(message);
        } else {
            var chatId = message.getChatId();
            var text = message.getText();
            var encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
            var json = mapper.writeValueAsString(keyboardMarkup);
            var keyboardAsJson = "{\"reply_markup\":" + json + "}";

            HttpRequest sendMessage = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(telegramApiEndpoint + privateToken + SENDMESSAGE.getParamPathSendMessage(chatId, encodedText)))
                    .POST(HttpRequest.BodyPublishers.ofString(keyboardAsJson))
                    .build();
            try {
                httpClient.send(sendMessage, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

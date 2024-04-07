package org.birthdayreminder.client.impl;

import org.birthdayreminder.client.TelegramRestClient;
import org.birthdayreminder.client.TelegramRestClientSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = {8888})
class TelegramRestClientImplTest {

    private final ClientAndServer mockserver;
    private final TelegramRestClient client = new TelegramRestClientImpl(new TelegramRestClientSettings("http://localhost:8888/", "bot123456789", 2));

    private static final String CORRECT_JSON_LINK = "src/test/resources/responses/Correct_ResultDTO.json";
    private static final String INCORRECT_JSON_LINK = "src/test/resources/responses/Incorrect_ResultDTO.json";

    public TelegramRestClientImplTest(ClientAndServer mockserver) {
        this.mockserver = mockserver;
    }

    final int OFFSET = 101180636;

    @BeforeEach
    void serverClear() {
        mockserver.clear(request());
    }

    @Test
    void shouldCallGetUpdatesMethodInvalidResultInvalidJSONField() throws IOException {
        //Given
        mockserver.when(
                request()
                        .withMethod("GET")
                        .withPath("/bot123456789/getUpdates")
                        .withPathParameter("offset", "101180636")
        ).respond(
                response().withBody(Files.readString(Paths.get(INCORRECT_JSON_LINK))));
        //When
        var actual = client.getUpdates(OFFSET);
        //Then
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void shouldCallGetUpdatesMethod() throws IOException {
        //Given
        mockserver.when(
                request()
                        .withMethod("GET")
                        .withPath("/bot123456789/getUpdates")
                        .withQueryStringParameter("offset", "101180636") //вместо pathparam
        ).respond(
                response().withBody(Files.readString(Paths.get(CORRECT_JSON_LINK))));
        //When
        var actual = client.getUpdates(OFFSET);
        //Then
        Assertions.assertEquals(6, actual.size());
        Assertions.assertEquals("1", actual.get(0).getMessage().getText());
    }
}
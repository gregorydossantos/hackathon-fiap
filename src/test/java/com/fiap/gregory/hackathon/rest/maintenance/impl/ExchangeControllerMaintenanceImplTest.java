package com.fiap.gregory.hackathon.rest.maintenance.impl;

import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;
import com.fiap.gregory.hackathon.rest.jms.sender.IExchangeSenderMessage;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_EXCHANGES;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeControllerMaintenanceImplTest {

    @LocalServerPort
    int port;

    @MockBean
    IExchangeSenderMessage senderMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssured.port = port;
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 201 - CREATED")
    void should_ReturnsHttp201_When_SendMessageOk() {
        var request = Mockito.mock(ExchangeRequest.class);
        doNothing().when(senderMessage).sendMessage(request);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(PATH_EXCHANGES)
                .then().statusCode(HttpStatus.CREATED.value());

        verify(senderMessage).sendMessage(any(ExchangeRequest.class));
    }

}
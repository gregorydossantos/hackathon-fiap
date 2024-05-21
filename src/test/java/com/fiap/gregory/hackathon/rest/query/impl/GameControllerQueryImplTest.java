package com.fiap.gregory.hackathon.rest.query.impl;

import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import com.fiap.gregory.hackathon.service.query.IGameServiceQuery;
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

import java.util.List;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_GAMES;
import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerQueryImplTest {

    @LocalServerPort
    int port;

    @MockBean
    IGameServiceQuery serviceQuery;

    GameResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        response = Mockito.mock(GameResponse.class);
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should be return a HTTP status 200 - SUCCESS")
    void getGames() {
        when(serviceQuery.getGames()).thenReturn(List.of(response));

        given()
                .contentType(ContentType.JSON)
                .when().get(PATH_GAMES)
                .then().statusCode(HttpStatus.OK.value());
    }

}
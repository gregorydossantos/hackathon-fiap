package com.fiap.gregory.hackathon.rest.maintenance.impl;

import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import com.fiap.gregory.hackathon.service.maintenance.IGameServiceMaintenance;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_GAMES;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerMaintenanceImplTest {
    private static final String PATH_GAME_ID = PATH_GAMES + "/1";

    @LocalServerPort
    int port;

    @MockBean
    IGameServiceMaintenance gameServiceMaintenance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssured.port = port;
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 201 - CREATED")
    void should_Returns_Http_201_When_CreateGame() {
        var request = GameRequest.builder()
                .name("Test")
                .brand("Mega Driver")
                .user_id(1L)
                .build();
        doNothing().when(gameServiceMaintenance).createGame(request);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(PATH_GAMES)
                .then().statusCode(HttpStatus.CREATED.value());

        verify(gameServiceMaintenance).createGame(request);
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 200 - OK")
    void should_Returns_Http_200_When_DeleteGame() {
        doNothing().when(gameServiceMaintenance).deleteGame(1L);

        given()
                .contentType(ContentType.JSON)
                .when().delete(PATH_GAME_ID)
                .then().statusCode(HttpStatus.OK.value());

        verify(gameServiceMaintenance).deleteGame(anyLong());
    }

}
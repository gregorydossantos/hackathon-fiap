package com.fiap.gregory.hackathon.rest.exceptionhandler;

import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class GameExceptionHandlerTest {

    @InjectMocks
    GameExceptionHandler gameExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should be return GameNotFoundException")
    void should_ReturnsGameNotFoundException_When_GameNotFound() {
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.gameNotFoundException(
                new GameNotFoundException(GAME_NOT_FOUND));

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Should be return GameDataIntegrityException")
    void should_ReturnsGameDataIntegrityException_When_GameDataIntegrityException() {
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.gameDataIntegrityException(
                new GameDataIntegrityException(GAME_ALREADY_REGISTER));

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    @DisplayName("Should be return MethodArgumentNotValidException")
    void should_Returns_MethodArgumentNotValidException_When_Has_Error_In_Game_Payload() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        FieldError nameError =
                new FieldError("gameRequest", "name", "Name is required");

        FieldError emailError =
                new FieldError("gameRequest", "brand", "Brand is required");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(nameError, emailError));

        ResponseEntity<Map<String, String>> response = gameExceptionHandler.gamePayloadException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Name is required", response.getBody().get("name"));
        assertEquals("Brand is required", response.getBody().get("brand"));
        assertEquals(2, response.getBody().size());
    }
}
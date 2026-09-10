package com.fiap.gregory.hackathon.rest.exceptionhandler;

import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserBadRequestException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
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
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.BAD_REQUEST;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class UserExceptionHandlerTest {

    @InjectMocks
    UserExceptionHandler userExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("EXCEPTION HANDLER ::: Should be return UserDataIntegrityException")
    void should_ReturnsUserDataIntegrityException_When_UserAlreadyExists() {
        ResponseEntity<ErrorResponse> response = userExceptionHandler.userDataIntegrityException(
                new UserDataIntegrityException(USER_ALREADY_REGISTER));

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("EXCEPTION HANDLER ::: Should be return UserNotFoundException")
    void should_ReturnsUserNotFoundException_When_UserNotFound() {
        ResponseEntity<ErrorResponse> response = userExceptionHandler.userNotFoundException(
                new UserNotFoundException(USER_NOT_FOUND));

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("EXCEPTION HANDLER ::: Should be return UserNotFoundException")
    void should_ReturnsUserBadRequestException_When_UserBadRequest() {
        ResponseEntity<ErrorResponse> response = userExceptionHandler.userBadRequestException(
                new UserBadRequestException(BAD_REQUEST));

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("EXCEPTION HANDLER ::: Should be return MethodArgumentNotValidException")
    void should_Returns_MethodArgumentNotValidException_When_Has_Error_In_User_Payload() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        FieldError nameError =
                new FieldError("userRequest", "name", "Name is required");

        FieldError emailError =
                new FieldError("userRequest", "email", "Email is invalid");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(nameError, emailError));

        ResponseEntity<Map<String, String>> response = userExceptionHandler.userPayloadException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Name is required", response.getBody().get("name"));
        assertEquals("Email is invalid", response.getBody().get("email"));
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("EXCEPTION HANDLER ::: Should be return HttpRequestMethodNotSupportedException")
    void should_Returns_HttpRequestMethodNotSupportedException_When_Receive_Bad_Request() {
        ResponseEntity<ErrorResponse> response = userExceptionHandler.httpRequestMethodNotSupportedException(
                new HttpRequestMethodNotSupportedException(BAD_REQUEST));

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
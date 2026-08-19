package com.fiap.gregory.hackathon.rest.exceptionhandler;

import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserBadRequestException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_INVALID;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserDataIntegrityException.class)
    public ResponseEntity<ErrorResponse> userDataIntegrityException(final UserDataIntegrityException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(final UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserBadRequestException.class)
    public ResponseEntity<ErrorResponse> userBadRequestException(final UserBadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> userBadRequestException(final MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(EMAIL_INVALID);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

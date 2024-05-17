package com.fiap.gregory.hackathon.rest.exceptionhandler.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}

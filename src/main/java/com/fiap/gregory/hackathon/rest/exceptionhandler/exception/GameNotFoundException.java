package com.fiap.gregory.hackathon.rest.exceptionhandler.exception;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String message) {
        super(message);
    }

}

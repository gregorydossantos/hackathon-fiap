package com.fiap.gregory.hackathon.rest.exceptionhandler.exception;

public class UserBadRequestException extends RuntimeException {

    public UserBadRequestException(String message) {
        super(message);
    }

}

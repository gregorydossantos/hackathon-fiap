package com.fiap.gregory.hackathon.domain.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_INVALID;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_REGEX;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

class UserMessageTest {

    @Test
    @DisplayName("Testing the commons message")
    void commonsMessageTest() {
        assertThat("User already register!".equalsIgnoreCase(USER_ALREADY_REGISTER)).isTrue();
        assertThat("Email already register!".equalsIgnoreCase(EMAIL_ALREADY_REGISTER)).isTrue();
        assertThat("Invalid email!".equalsIgnoreCase(EMAIL_INVALID)).isTrue();
        assertThat("User not found!".equalsIgnoreCase(USER_NOT_FOUND)).isTrue();
        assertThat("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$".equalsIgnoreCase(EMAIL_REGEX)).isTrue();
    }
}
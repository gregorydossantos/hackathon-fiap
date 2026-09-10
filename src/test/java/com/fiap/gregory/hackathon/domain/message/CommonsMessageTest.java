package com.fiap.gregory.hackathon.domain.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.BAD_REQUEST;
import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.ENUMS_VALIDATED;
import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.FIELD_MANDATORY;
import static org.assertj.core.api.Assertions.assertThat;


class CommonsMessageTest {

    @Test
    @DisplayName("Testing the commons message")
    void commonsMessageTest() {
        assertThat("Is a mandatory field!".equalsIgnoreCase(FIELD_MANDATORY)).isTrue();
        assertThat("Codes accepted [0, 1, 2]!".equalsIgnoreCase(ENUMS_VALIDATED)).isTrue();
        assertThat("Bad request!".equalsIgnoreCase(BAD_REQUEST)).isTrue();
    }
}
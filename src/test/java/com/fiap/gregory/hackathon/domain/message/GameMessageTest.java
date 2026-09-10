package com.fiap.gregory.hackathon.domain.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

class GameMessageTest {

    @Test
    @DisplayName("Testing the game message")
    void gameMessageTest() {
        assertThat("Game not found!".equalsIgnoreCase(GAME_NOT_FOUND)).isTrue();
        assertThat("Game already register!".equalsIgnoreCase(GAME_ALREADY_REGISTER)).isTrue();
    }
}
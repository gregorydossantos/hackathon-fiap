package com.fiap.gregory.hackathon.rest.jms.sender.impl;

import com.fiap.gregory.hackathon.infra.db.model.GameEntity;
import com.fiap.gregory.hackathon.infra.db.model.UserEntity;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ExchangeSenderMessageImplTest {

    @InjectMocks
    ExchangeSenderMessageImpl senderMessage;

    @Mock
    JmsTemplate jmsTemplate;

    @Mock
    IUserRepository userRepository;

    @Mock
    IGameRepository gameRepository;

    ExchangeRequest request;
    UserEntity user;
    GameEntity game;
    String userUuid;
    String gameUuid;

    @BeforeEach
    void setUp() {
        userUuid = UUID.randomUUID().toString();
        gameUuid = UUID.randomUUID().toString();

        request = ExchangeRequest.builder()
                .userId(userUuid.toString())
                .gameId(gameUuid.toString())
                .build();

        user = UserEntity.builder()
                .id(1L)
                .userId(userUuid)
                .name("User")
                .email("user@user.com")
                .password("password")
                .exchange("Email")
                .build();

        game = GameEntity.builder()
                .id(1L)
                .gameId(gameUuid)
                .name("Game")
                .brand("Brand")
                .userId(userUuid)
                .build();
    }

    @Test
    @DisplayName("MESSAGING LAYER ::: Send a message")
    void should_SendMessage_When_Method_sendMessage_isCall() {
        when(userRepository.findByUserId(anyString())).thenReturn(Optional.ofNullable(user));
        when(gameRepository.findByGameId(anyString())).thenReturn(Optional.ofNullable(game));

        senderMessage.sendMessage(request);
        verify(userRepository).findByUserId(anyString());
        verify(gameRepository).findByGameId(anyString());
    }

    @Test
    @DisplayName("MESSAGING LAYER ::: UserNotFoundException")
    void throw_UserNotFoundException_When_sendMessage_notHasUser() {
        when(userRepository.findByUserId(anyString())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> senderMessage.sendMessage(request));
    }

    @Test
    @DisplayName("MESSAGING LAYER ::: GameNotFoundException")
    void throw_GameNotFoundException_When_sendMessage_notHasGame() {
        when(userRepository.findByUserId(anyString())).thenReturn(Optional.ofNullable(user));
        when(gameRepository.findByGameId(anyString())).thenReturn(Optional.empty());
        assertThrows(GameNotFoundException.class, () -> senderMessage.sendMessage(request));
    }
}
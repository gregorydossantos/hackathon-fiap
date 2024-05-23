package com.fiap.gregory.hackathon.rest.jms.sender.impl;

import com.fiap.gregory.hackathon.infra.db.model.Games;
import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
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

    @Test
    @DisplayName("MESSAGING LAYER ::: Send a message")
    void sendMessage() {
        var request = Mockito.mock(ExchangeRequest.class);

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Mockito.mock(Users.class)));
        when(gameRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Mockito.mock(Games.class)));

        senderMessage.sendMessage(request);
    }

}
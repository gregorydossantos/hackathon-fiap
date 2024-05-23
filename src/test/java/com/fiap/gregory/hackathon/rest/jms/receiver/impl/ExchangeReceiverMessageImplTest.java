package com.fiap.gregory.hackathon.rest.jms.receiver.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class ExchangeReceiverMessageImplTest {

    @MockBean
    ExchangeReceiverMessageImpl receiverMessage;

    @Test
    @DisplayName("MESSAGING LAYER ::: Receive a message")
    void receiveMessage() {
        receiverMessage.receiveMessage("Test");
        verify(receiverMessage).receiveMessage(anyString());
    }
}
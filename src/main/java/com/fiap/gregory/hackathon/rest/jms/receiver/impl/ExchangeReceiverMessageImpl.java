package com.fiap.gregory.hackathon.rest.jms.receiver.impl;

import com.fiap.gregory.hackathon.rest.jms.receiver.IExchangeReceiverMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExchangeReceiverMessageImpl implements IExchangeReceiverMessage {

    @Override
    public void receiveMessage(String message) {
        log.info("Message receive: {}", message);
    }

}

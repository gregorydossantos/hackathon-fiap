package com.fiap.gregory.hackathon.rest.jms.receiver;

import org.springframework.jms.annotation.JmsListener;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_JMS_SENDER;

public interface IExchangeReceiverMessage {

    @JmsListener(destination = PATH_JMS_SENDER, containerFactory = "myFactory")
    void receiveMessage(String message);

}

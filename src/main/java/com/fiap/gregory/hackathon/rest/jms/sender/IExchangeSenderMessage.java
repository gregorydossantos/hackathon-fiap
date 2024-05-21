package com.fiap.gregory.hackathon.rest.jms.sender;

import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;

public interface IExchangeSenderMessage {

    void sendMessage(ExchangeRequest request);

}

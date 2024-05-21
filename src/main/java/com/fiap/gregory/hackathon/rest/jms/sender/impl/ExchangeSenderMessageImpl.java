package com.fiap.gregory.hackathon.rest.jms.sender.impl;

import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;
import com.fiap.gregory.hackathon.rest.jms.sender.IExchangeSenderMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_JMS_SENDER;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExchangeSenderMessageImpl implements IExchangeSenderMessage {

    JmsTemplate template;

    @Override
    public void sendMessage(ExchangeRequest request) {
        template.convertAndSend(PATH_JMS_SENDER, request);
    }

}

package com.fiap.gregory.hackathon.rest.jms.sender.impl;

import com.fiap.gregory.hackathon.infra.db.model.Games;
import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import com.fiap.gregory.hackathon.rest.jms.sender.IExchangeSenderMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_NOT_FOUND;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_NOT_FOUND;
import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_JMS_SENDER;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExchangeSenderMessageImpl implements IExchangeSenderMessage {

    IUserRepository userRepository;
    IGameRepository gameRepository;
    JmsTemplate template;

    @Override
    public void sendMessage(ExchangeRequest request) {
        var user = getUser(request.getUser_id());
        var game = getGame(request.getGame_id());

        var message = buildMessage(user, game);
        template.convertAndSend(PATH_JMS_SENDER, message);
    }

    private Users getUser(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        return user.get();
    }

    private Games getGame(Long id) {
        var game = gameRepository.findById(id);
        if (game.isEmpty()) {
            throw new GameNotFoundException(GAME_NOT_FOUND);
        }

        return game.get();
    }

    private String buildMessage(Users user, Games game) {
        return "The " + user.getName() +
                "wants to trade the " + game.getName() +
                "with you!";
    }

}

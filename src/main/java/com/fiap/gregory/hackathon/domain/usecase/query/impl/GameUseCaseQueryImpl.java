package com.fiap.gregory.hackathon.domain.usecase.query.impl;

import com.fiap.gregory.hackathon.domain.mapper.IGameMapper;
import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.domain.usecase.query.IGameUseCaseQuery;
import com.fiap.gregory.hackathon.infra.db.model.Games;
import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_NOT_FOUND;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameUseCaseQueryImpl implements IGameUseCaseQuery {

    IGameRepository gameRepository;
    IGameMapper gameMapper;

    @Override
    public List<GameResponse> getGames() {
        var games = gameRepository.findAll();
        if (games.isEmpty()) {
            throw new GameNotFoundException(GAME_NOT_FOUND);
        }

        return gameMapper.toListResponse(games);
    }

}

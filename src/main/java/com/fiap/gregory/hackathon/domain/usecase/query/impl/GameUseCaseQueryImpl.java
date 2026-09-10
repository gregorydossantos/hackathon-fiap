package com.fiap.gregory.hackathon.domain.usecase.query.impl;

import com.fiap.gregory.hackathon.domain.mapper.IGameMapper;
import com.fiap.gregory.hackathon.domain.usecase.query.IGameUseCaseQuery;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.rest.dto.response.GameDataResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_NOT_FOUND;

@Log4j2
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameUseCaseQueryImpl implements IGameUseCaseQuery {

    IGameRepository gameRepository;
    IGameMapper gameMapper;

    @Override
    //@Cacheable("games")
    public GameDataResponse getGames(int page, int size) {
        log.info("Get all games from database");
        var games = gameRepository.findAll(setPageable(page, size));

        log.info("Validate if return of database is empty");
        if (games.isEmpty()) {
            throw new GameNotFoundException(GAME_NOT_FOUND);
        }

        log.info("Convert entity to response and send the answer for the API");
        return GameDataResponse.builder()
                .games(gameMapper.toListResponse(games.getContent()))
                .build();
    }

    private Pageable setPageable(int page, int size) {
        return PageRequest.of(page, size);
    }
}

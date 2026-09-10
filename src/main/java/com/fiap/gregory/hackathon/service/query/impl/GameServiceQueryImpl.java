package com.fiap.gregory.hackathon.service.query.impl;

import com.fiap.gregory.hackathon.domain.usecase.query.IGameUseCaseQuery;
import com.fiap.gregory.hackathon.rest.dto.response.GameDataResponse;
import com.fiap.gregory.hackathon.service.query.IGameServiceQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameServiceQueryImpl implements IGameServiceQuery {

    IGameUseCaseQuery useCaseQuery;

    @Override
    public GameDataResponse getGames(int page, int size) {
        return useCaseQuery.getGames(page, size);
    }
}

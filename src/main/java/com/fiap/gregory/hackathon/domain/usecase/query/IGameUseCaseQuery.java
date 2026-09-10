package com.fiap.gregory.hackathon.domain.usecase.query;

import com.fiap.gregory.hackathon.rest.dto.response.GameDataResponse;

public interface IGameUseCaseQuery {
    GameDataResponse getGames(int page, int size);
}

package com.fiap.gregory.hackathon.service.query;

import com.fiap.gregory.hackathon.rest.dto.response.GameDataResponse;

public interface IGameServiceQuery {
    GameDataResponse getGames(int page, int size);
}

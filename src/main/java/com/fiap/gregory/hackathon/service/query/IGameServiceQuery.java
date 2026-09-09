package com.fiap.gregory.hackathon.service.query;

import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;

import java.util.List;

public interface IGameServiceQuery {
    List<GameResponse> getGames(int page, int size);
}

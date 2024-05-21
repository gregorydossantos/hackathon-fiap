package com.fiap.gregory.hackathon.domain.usecase.query;

import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

import java.util.List;

public interface IGameUseCaseQuery {

    List<GameResponse> getGames();

}

package com.fiap.gregory.hackathon.service.query;

import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

import java.util.List;

public interface IGameServiceQuery {

    List<GameResponse> getGames();

}
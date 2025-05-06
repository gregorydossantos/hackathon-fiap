package com.fiap.gregory.hackathon.service.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;

public interface IGameServiceMaintenance {

    void createGame(GameRequest request);

    void deleteGame(Long id);

}

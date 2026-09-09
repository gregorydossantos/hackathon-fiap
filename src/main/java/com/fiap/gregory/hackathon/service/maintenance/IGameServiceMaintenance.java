package com.fiap.gregory.hackathon.service.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;

import java.util.UUID;

public interface IGameServiceMaintenance {
    void createGame(GameRequest request);

    void deleteGame(UUID id);
}

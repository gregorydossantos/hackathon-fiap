package com.fiap.gregory.hackathon.domain.usecase.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;

import java.util.UUID;

public interface IGameUseCaseMaintenance {
    void createGame(GameRequest request);

    void deleteGame(UUID id);
}

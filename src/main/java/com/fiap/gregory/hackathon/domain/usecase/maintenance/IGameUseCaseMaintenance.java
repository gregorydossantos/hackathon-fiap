package com.fiap.gregory.hackathon.domain.usecase.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;

public interface IGameUseCaseMaintenance {
    void createGame(GameRequest request);

    void deleteGame(Long id);
}

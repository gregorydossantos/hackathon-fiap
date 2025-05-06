package com.fiap.gregory.hackathon.service.maintenance.impl;

import com.fiap.gregory.hackathon.domain.usecase.maintenance.IGameUseCaseMaintenance;
import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import com.fiap.gregory.hackathon.service.maintenance.IGameServiceMaintenance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameServiceMaintenanceImpl implements IGameServiceMaintenance {

    IGameUseCaseMaintenance gameMaintenanceUseCase;

    @Override
    public void createGame(GameRequest request) {
        gameMaintenanceUseCase.createGame(request);
    }

    @Override
    public void deleteGame(Long id) {
        gameMaintenanceUseCase.deleteGame(id);
    }
}

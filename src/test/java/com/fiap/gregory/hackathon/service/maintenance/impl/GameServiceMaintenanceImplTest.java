package com.fiap.gregory.hackathon.service.maintenance.impl;

import com.fiap.gregory.hackathon.domain.usecase.maintenance.IGameUseCaseMaintenance;
import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class GameServiceMaintenanceImplTest {

    @Mock
    IGameUseCaseMaintenance useCaseMaintenance;

    @InjectMocks
    GameServiceMaintenanceImpl gameServiceMaintenance;

    @Test
    @DisplayName("SERVICE LAYER ::: Create a game successfully")
    void should_Returns_Http_201_When_Call_Method_CreateGame() {
        var request = Mockito.mock(GameRequest.class);
        doNothing().when(useCaseMaintenance).createGame(request);

        gameServiceMaintenance.createGame(request);
        verify(useCaseMaintenance).createGame(request);
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Delete a game successfully")
    void should_Returns_Http_200_When_Call_Method_DeleteGame() {
        var id = UUID.randomUUID();
        doNothing().when(useCaseMaintenance).deleteGame(id);

        gameServiceMaintenance.deleteGame(id);
        verify(useCaseMaintenance).deleteGame(id);
    }

}
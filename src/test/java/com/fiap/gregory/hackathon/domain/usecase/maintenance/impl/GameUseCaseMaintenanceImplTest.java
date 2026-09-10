package com.fiap.gregory.hackathon.domain.usecase.maintenance.impl;

import com.fiap.gregory.hackathon.domain.mapper.IGameMapper;
import com.fiap.gregory.hackathon.infra.db.model.GameEntity;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class GameUseCaseMaintenanceImplTest {

    @Mock
    IGameRepository repository;

    @Mock
    IGameMapper mapper;

    @InjectMocks
    GameUseCaseMaintenanceImpl gameUseCaseMaintenance;

    GameRequest requestMock;
    GameResponse responseMock;
    GameEntity gameMock;
    UUID gameIdMock;
    UUID userIdMock;

    @BeforeEach
    void setUp() {
        gameIdMock = UUID.randomUUID();
        userIdMock = UUID.randomUUID();
        requestMock = GameRequest.builder()
                .name("Fifa 25")
                .brand("PS5")
                .userId(userIdMock.toString())
                .build();

        responseMock = GameResponse.builder()
                .id(1L)
                .gameId(gameIdMock)
                .name("Fifa 25")
                .brand("PS5")
                .userId(userIdMock)
                .build();

        gameMock = GameEntity.builder()
                .id(1L)
                .gameId(gameIdMock)
                .name("Fifa 25")
                .brand("PS5")
                .userId(userIdMock)
                .build();
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Create a game")
    void should_Create_A_Game_When_Call_CreateGame_Method() {
        when(repository.findByName(requestMock.getName())).thenReturn(Optional.empty());
        when(mapper.toEntity(requestMock)).thenReturn(gameMock);

        gameUseCaseMaintenance.createGame(requestMock);
        verify(repository).save(gameMock);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Delete a game")
    void should_Delete_A_Game_When_Call_DeleteGame_Method() {
        when(repository.findByGameId(gameIdMock)).thenReturn(Optional.of(gameMock));

        gameUseCaseMaintenance.deleteGame(gameIdMock);
        verify(repository).delete(gameMock);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: GameDataIntegrityException [CREATE]")
    void throws_GameDataIntegrityException_When_Create_A_Game() {
        when(repository.findByName(anyString())).thenReturn(Optional.ofNullable(gameMock));
        assertThrows(GameDataIntegrityException.class, () -> gameUseCaseMaintenance.createGame(requestMock));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: GameNotFoundException [DELETE]")
    void throws_GameNotFoundException_When_Delete_A_Game() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(GameNotFoundException.class, () -> gameUseCaseMaintenance.deleteGame(gameIdMock));
    }
}
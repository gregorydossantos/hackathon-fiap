package com.fiap.gregory.hackathon.domain.usecase.maintenance.impl;

import com.fiap.gregory.hackathon.domain.mapper.IGameMapper;
import com.fiap.gregory.hackathon.domain.usecase.maintenance.IGameUseCaseMaintenance;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.GameMessage.GAME_NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameUseCaseMaintenanceImpl implements IGameUseCaseMaintenance {

    IGameRepository gameRepository;
    IGameMapper mapper;

    @Override
    public void createGame(GameRequest request) {
        log.info("====[CREATE GAME]====");
        log.info("Validate if game already exists by name {}", request.getName());
        if (gameExists(request.getName())) {
            throw new GameDataIntegrityException(GAME_ALREADY_REGISTER);
        }

        log.info("Convert request in entity");
        var game = mapper.toEntity(request);

        log.info("Persist entity at database: {}", game);
        gameRepository.save(game);
    }

    @Override
    public void deleteGame(UUID id) {
        log.info("====[DELETE GAME]====");
        log.info("Get game by ID {}", id);
        var user = gameRepository.findByGameId(id.toString());

        log.info("[DELETE] ==== Validating if exists register on database");
        if (user.isEmpty()) {
            throw new GameNotFoundException(GAME_NOT_FOUND);
        }

        log.info("Deleting game from database");
        gameRepository.delete(user.get());
    }

    private boolean gameExists(String name) {
        return gameRepository.findByName(name).isPresent();
    }
}

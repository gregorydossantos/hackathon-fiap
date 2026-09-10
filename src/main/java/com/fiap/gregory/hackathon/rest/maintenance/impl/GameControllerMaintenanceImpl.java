package com.fiap.gregory.hackathon.rest.maintenance.impl;

import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import com.fiap.gregory.hackathon.rest.maintenance.IGameControllerMaintenance;
import com.fiap.gregory.hackathon.service.maintenance.IGameServiceMaintenance;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_GAMES;

@RestController
@AllArgsConstructor
@Tag(name = "Game Controller")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = PATH_GAMES, produces = {"application/json"})
public class GameControllerMaintenanceImpl implements IGameControllerMaintenance {

    IGameServiceMaintenance gameServiceMaintenance;

    @Operation(summary = "Create a game", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return HTTP status CREATED"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @Override
    public ResponseEntity<Void> createGame(GameRequest request) {
        gameServiceMaintenance.createGame(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Delete a game", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a game"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @Override
    public ResponseEntity<Void> deleteGame(UUID id) {
        gameServiceMaintenance.deleteGame(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

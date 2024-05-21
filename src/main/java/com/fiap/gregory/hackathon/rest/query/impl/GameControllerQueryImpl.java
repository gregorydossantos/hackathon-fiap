package com.fiap.gregory.hackathon.rest.query.impl;

import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import com.fiap.gregory.hackathon.rest.query.IGameControllerQuery;
import com.fiap.gregory.hackathon.service.query.IGameServiceQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_GAMES;

@RestController
@AllArgsConstructor
@Tag(name = "Game Controller")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = PATH_GAMES, produces = {"application/json"})
public class GameControllerQueryImpl implements IGameControllerQuery {

    IGameServiceQuery serviceQuery;

    @Operation(summary = "Get a list of games", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of games"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @Override
    public ResponseEntity<List<GameResponse>> getGames() {
        var response = serviceQuery.getGames();
        return ResponseEntity.ok().body(response);
    }

}

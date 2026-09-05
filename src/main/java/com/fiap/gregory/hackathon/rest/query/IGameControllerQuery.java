package com.fiap.gregory.hackathon.rest.query;

import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IGameControllerQuery {

    @GetMapping
    ResponseEntity<List<GameResponse>> getGames(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size);

}

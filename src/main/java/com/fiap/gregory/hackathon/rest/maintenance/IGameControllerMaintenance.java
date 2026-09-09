package com.fiap.gregory.hackathon.rest.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface IGameControllerMaintenance {
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createGame(@Valid @RequestBody GameRequest request);

    @DeleteMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteGame(@PathVariable("id") UUID id);
}

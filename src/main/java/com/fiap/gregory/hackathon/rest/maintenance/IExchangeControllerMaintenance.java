package com.fiap.gregory.hackathon.rest.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface IExchangeControllerMaintenance {

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> sendMessage(@RequestBody @Valid ExchangeRequest request);

}

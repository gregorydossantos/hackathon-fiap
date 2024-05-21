package com.fiap.gregory.hackathon.rest.maintenance.impl;

import com.fiap.gregory.hackathon.rest.dto.request.ExchangeRequest;
import com.fiap.gregory.hackathon.rest.jms.sender.IExchangeSenderMessage;
import com.fiap.gregory.hackathon.rest.maintenance.IExchangeControllerMaintenance;
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

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_EXCHANGES;

@RestController
@AllArgsConstructor
@Tag(name = "Exchange Controller")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = PATH_EXCHANGES, produces = {"application/json"})
public class ExchangeControllerMaintenanceImpl implements IExchangeControllerMaintenance {

    IExchangeSenderMessage senderMessage;

    @Operation(summary = "Send a message to another user", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @Override
    public ResponseEntity<Void> sendMessage(ExchangeRequest request) {
        senderMessage.sendMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

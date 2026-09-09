package com.fiap.gregory.hackathon.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.FIELD_MANDATORY;

@Data
@Builder
public class ExchangeRequest {

    @JsonProperty("user_id")
    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private String userId;

    @JsonProperty("game_id")
    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private String gameId;
}

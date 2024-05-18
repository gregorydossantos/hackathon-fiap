package com.fiap.gregory.hackathon.rest.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.FIELD_MANDATORY;

@Data
@Builder
public class GameRequest {

    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private String name;

    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private String brand;

    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private Long user_id;

}

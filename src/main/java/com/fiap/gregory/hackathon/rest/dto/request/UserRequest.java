package com.fiap.gregory.hackathon.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.ENUMS_VALIDATED;
import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.FIELD_MANDATORY;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_INVALID;

@Data
@Builder
public class UserRequest {

    @JsonProperty("name")
    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private String name;

    @JsonProperty("email")
    @Email(message = EMAIL_INVALID)
    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private String email;

    @JsonProperty("password")
    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    private String password;

    @JsonProperty("exchange_code")
    @Min(value = 0, message = ENUMS_VALIDATED)
    @Max(value = 2, message = ENUMS_VALIDATED)
    private int exchangeCode;
}

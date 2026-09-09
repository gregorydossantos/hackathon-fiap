package com.fiap.gregory.hackathon.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_INVALID;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_REGEX;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    @Email(regexp = EMAIL_REGEX, message = EMAIL_INVALID)
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("exchange_code")
    private int exchangeCode;
}

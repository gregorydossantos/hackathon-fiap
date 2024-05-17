package com.fiap.gregory.hackathon.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.*;

@Data
@Builder
public class UserRequest {

    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    @Size(max = 10, message = NAME_SIZE)
    private String name;

    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    @Email(message = EMAIL_INVALID)
    private String email;

    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    @Size(max = 8, message = PASSWORD_SIZE)
    private Integer password;

    @NotNull(message = FIELD_MANDATORY)
    @NotEmpty(message = FIELD_MANDATORY)
    @Size(max = 10, message = EXCHANGE_SIZE)
    private String exchange;

}

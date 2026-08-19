package com.fiap.gregory.hackathon.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.fiap.gregory.hackathon.domain.message.CommonsMessage.FIELD_MANDATORY;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.*;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateRequest {
    private String name;
    @Email(regexp = EMAIL_REGEX, message = EMAIL_INVALID)
    private String email;
    private String password;
    private String exchange;

}

package com.fiap.gregory.hackathon.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    @JsonProperty(access = WRITE_ONLY)
    private Long id;
    private UUID userId;
    private String name;
    private String email;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    private String exchange;
}

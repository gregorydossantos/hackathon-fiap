package com.fiap.gregory.hackathon.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @JsonProperty(access = WRITE_ONLY)
    private Long id;
    private String name;
    private String email;
    @JsonProperty(access = WRITE_ONLY)
    private Integer password;
    private String exchange;

}

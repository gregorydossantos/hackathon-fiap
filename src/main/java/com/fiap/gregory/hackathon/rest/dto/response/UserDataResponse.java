package com.fiap.gregory.hackathon.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public class UserDataResponse implements Serializable {

    @JsonProperty("users")
    private List<UserResponse> users;
}

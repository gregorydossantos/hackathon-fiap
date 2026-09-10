package com.fiap.gregory.hackathon.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public class GameDataResponse implements Serializable {

    @JsonProperty("games")
    private List<GameResponse> games;
}

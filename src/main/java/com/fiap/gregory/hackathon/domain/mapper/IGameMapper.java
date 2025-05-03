package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.Games;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGameMapper {

    List<GameResponse> toListResponse(List<Games> games);

}

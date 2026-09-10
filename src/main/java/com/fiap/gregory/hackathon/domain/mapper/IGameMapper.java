package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.GameEntity;
import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface IGameMapper {
    List<GameResponse> toListResponse(List<GameEntity> games);

    @Mapping(target = "gameId", expression = "java(UUID.randomUUID().toString())")
    GameEntity toEntity(GameRequest request);
}

package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.GameEntity;
import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface IGameMapper {
    List<GameResponse> toListResponse(List<GameEntity> games);

    @Mapping(source = "userId", target = "userId", qualifiedByName = "setUserId")
    GameEntity toEntity(GameRequest request);

    @Named("setUserId")
    static UUID setUserId(String id) {
        return UUID.fromString(id);
    }
}

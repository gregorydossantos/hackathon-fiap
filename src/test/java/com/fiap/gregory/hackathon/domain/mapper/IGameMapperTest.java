package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.Games;
import com.fiap.gregory.hackathon.rest.dto.request.GameRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class IGameMapperTest {

    @Autowired
    IGameMapper mapper;

    @Test
    @DisplayName("Should be map a entity to response list when call toListResponse() method")
    void Should_Be_Map_A_Entity_To_Response_List_When_Call_ToListResponse_Method() {
        var game = Mockito.mock(Games.class);
        var response = mapper.toListResponse(List.of(game));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Should be map a request to entity when call toEntity() method")
    void Should_Be_Map_A_Request_To_Entity_When_Call_ToEntity_Method() {
        var gameRequest = Mockito.mock(GameRequest.class);
        var response = mapper.toEntity(gameRequest);
        assertNotNull(response);
    }
}
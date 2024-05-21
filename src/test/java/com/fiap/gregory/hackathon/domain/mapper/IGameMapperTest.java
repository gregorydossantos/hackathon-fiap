package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.Games;
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
    void toListResponse() {
        var game = Mockito.mock(Games.class);
        var response = mapper.toListResponse(List.of(game));
        assertNotNull(response);
    }
}
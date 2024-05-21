package com.fiap.gregory.hackathon.service.query.impl;

import com.fiap.gregory.hackathon.domain.usecase.query.IGameUseCaseQuery;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class GameServiceQueryImplTest {

    @Mock
    IGameUseCaseQuery useCaseQuery;

    @InjectMocks
    GameServiceQueryImpl serviceQuery;

    @Test
    @DisplayName("SERVICE LAYER ::: Get a list of games")
    void getGames() {
        when(useCaseQuery.getGames()).thenReturn(List.of(Mockito.mock(GameResponse.class)));

        var response = serviceQuery.getGames();
        assertNotNull(response);
    }

}
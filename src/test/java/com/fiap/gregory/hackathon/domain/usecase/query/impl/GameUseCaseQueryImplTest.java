package com.fiap.gregory.hackathon.domain.usecase.query.impl;

import com.fiap.gregory.hackathon.domain.mapper.IGameMapper;
import com.fiap.gregory.hackathon.infra.db.model.Games;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class GameUseCaseQueryImplTest {

    @Mock
    IGameRepository repository;

    @Mock
    IGameMapper mapper;

    @InjectMocks
    GameUseCaseQueryImpl useCaseQuery;

    @Test
    @DisplayName("USE CASE LAYER ::: Get a list of games")
    void getGames() {
        when(repository.findAll(mock(Pageable.class))).thenReturn(mock(Page.class));
        when(mapper.toListResponse(anyList())).thenReturn(List.of(mock(GameResponse.class)));

        var response = useCaseQuery.getGames(0, 10);
        assertNotNull(response);
    }
    
}
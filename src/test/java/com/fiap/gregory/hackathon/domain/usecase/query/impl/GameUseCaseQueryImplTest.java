package com.fiap.gregory.hackathon.domain.usecase.query.impl;

import com.fiap.gregory.hackathon.domain.mapper.IGameMapper;
import com.fiap.gregory.hackathon.infra.db.model.Games;
import com.fiap.gregory.hackathon.infra.db.repository.IGameRepository;
import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.GameNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    void should_return_a_list_of_games() {
        Page<Games> mockPage = new PageImpl<>(List.of(mock(Games.class)));

        when(repository.findAll(any(Pageable.class))).thenReturn(mockPage);
        when(mapper.toListResponse(anyList())).thenReturn(List.of(mock(GameResponse.class)));

        var response = useCaseQuery.getGames(0, 10);
        assertNotNull(response);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Should throw GameNotFoundException")
    void should_throw_GameNotFoundException() {
        Page<Games> mockPage = new PageImpl<>(Collections.emptyList());

        when(repository.findAll(any(Pageable.class))).thenReturn(mockPage);

        assertThrows(GameNotFoundException.class, () -> useCaseQuery.getGames(0, 10));
    }

}
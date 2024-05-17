package com.fiap.gregory.hackathon.domain.usecase.query.impl;

import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class UserQueryUseCaseImplTest {

    @Mock
    IUserRepository userRepository;

    @Mock
    IUserMapper mapper;

    @InjectMocks
    UserQueryUseCaseImpl useCase;

    @Test
    @DisplayName("USE CASE LAYER ::: Get a list of users successfully")
    void should_ReturnsAListOfUsers_When_CallGetUsers() {
        when(userRepository.findAll()).thenReturn(List.of(Mockito.mock(Users.class)));
        when(mapper.toListResponse(anyList())).thenReturn(List.of(Mockito.mock(UserResponse.class)));

        var response = useCase.getUsers();
        assertNotNull(response);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Not found any user")
    void should_ReturnsUserNotFoundException_When_UsersNotExists() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(UserNotFoundException.class, () -> useCase.getUsers());
    }

}
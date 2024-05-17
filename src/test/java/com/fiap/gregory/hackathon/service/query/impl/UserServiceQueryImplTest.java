package com.fiap.gregory.hackathon.service.query.impl;

import com.fiap.gregory.hackathon.domain.usecase.query.IUserQueryUseCase;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
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
class UserServiceQueryImplTest {

    @Mock
    IUserQueryUseCase userQueryUseCase;

    @InjectMocks
    UserServiceQueryImpl userServiceQuery;

    @Test
    @DisplayName("SERVICE LAYER ::: Get a list of users successfully")
    void should_ReturnsAListOfUsers_When_CallGetUsers() {
        when(userQueryUseCase.getUsers()).thenReturn(List.of(Mockito.mock(UserResponse.class)));

        var response = userServiceQuery.getUsers();
        assertNotNull(response);
    }

}
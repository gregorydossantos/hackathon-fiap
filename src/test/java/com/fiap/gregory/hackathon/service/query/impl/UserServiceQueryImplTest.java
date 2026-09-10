package com.fiap.gregory.hackathon.service.query.impl;

import com.fiap.gregory.hackathon.domain.usecase.query.IUserUseCaseQuery;
import com.fiap.gregory.hackathon.rest.dto.response.UserDataResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceQueryImplTest {

    @Mock
    IUserUseCaseQuery userQueryUseCase;

    @InjectMocks
    UserServiceQueryImpl userServiceQuery;

    @Test
    @DisplayName("SERVICE LAYER ::: Get a list of users successfully")
    void should_ReturnsAListOfUsers_When_CallGetUsers() {
        when(userQueryUseCase.getUsers(0, 10)).thenReturn(Mockito.mock(UserDataResponse.class));

        var response = userServiceQuery.getUsers(0, 10);
        assertNotNull(response);
    }
}
package com.fiap.gregory.hackathon.service.maintenance.impl;

import com.fiap.gregory.hackathon.domain.usecase.maintenance.IUserUseCaseMaintenance;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceMaintenanceImplTest {

    @Mock
    IUserUseCaseMaintenance userMaintenanceUseCase;

    @InjectMocks
    UserServiceMaintenanceImpl userServiceMaintenance;

    @Test
    @DisplayName("SERVICE LAYER ::: Create a user successfully")
    void should_ReturnsHttp201_When_CallCreateUser() {
        var request = Mockito.mock(UserRequest.class);
        doNothing().when(userMaintenanceUseCase).createUser(request);

        userServiceMaintenance.createUser(request);
        verify(userMaintenanceUseCase).createUser(request);
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Update a user successfully")
    void should_ReturnsAUser_When_CallUpdateUser() {
        var request = Mockito.mock(UserRequest.class);
        var response = Mockito.mock(UserResponse.class);
        when(userMaintenanceUseCase.updateUser(1L, any())).thenReturn(response);

        var data = userServiceMaintenance.updateUser(1L, request);
        assertNotNull(data);
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Delete a user successfully")
    void should_ReturnsHttp200_When_CallDeleteUser() {
        doNothing().when(userMaintenanceUseCase).deleteUser(1L);

        userServiceMaintenance.deleteUser(1L);
        verify(userMaintenanceUseCase).deleteUser(1L);
    }

}
package com.fiap.gregory.hackathon.domain.usecase.maintenance.impl;

import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class UserMaintenanceUseCaseImplTest {

    @Mock
    IUserRepository userRepository;

    @Mock
    IUserMapper mapper;

    @InjectMocks
    UserUseCaseMaintenanceImpl userMaintenanceUseCase;

    UserRequest request;
    UserResponse response;
    Users user;

    @BeforeEach
    void setUp() {
        request = Mockito.mock(UserRequest.class);
        response = Mockito.mock(UserResponse.class);
        user = Mockito.mock(Users.class);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Create a user")
    void shouldCreateAUser_When_CallCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(mapper.toListResponse(anyList())).thenReturn(List.of(response));

        userMaintenanceUseCase.createUser(request);
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Update a user")
    void updateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(mapper.toUpdate(user, request)).thenReturn(user);

        var response = userMaintenanceUseCase.updateUser(1L, request);
        assertNotNull(response);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Delete a user")
    void deleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        userMaintenanceUseCase.deleteUser(1L);
        verify(userRepository).delete(Mockito.mock(Users.class));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: UserDataIntegrityException")
    void throwUserDataIntegrityException_When_CreateAUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserDataIntegrityException.class, () -> userMaintenanceUseCase.createUser(request));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: UserNotFoundException [UPDATE]")
    void throwUserNotFoundException_When_UpdateAUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userMaintenanceUseCase.updateUser(1L, request));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: UserNotFoundException [DELETE]")
    void throwUserNotFoundException_When_DeleteAUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userMaintenanceUseCase.deleteUser(1L));
    }

}
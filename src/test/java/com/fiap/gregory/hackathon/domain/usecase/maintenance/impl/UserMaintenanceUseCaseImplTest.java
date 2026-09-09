package com.fiap.gregory.hackathon.domain.usecase.maintenance.impl;

import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.infra.db.model.UserEntity;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import com.fiap.gregory.hackathon.service.encryption.IEncryptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Mock
    IEncryptionService encryptionService;

    @InjectMocks
    UserUseCaseMaintenanceImpl userMaintenanceUseCase;

    UserRequest userRequestMock;
    UserUpdateRequest userUpdateRequestMock;
    UserResponse responseMock;
    UserEntity userMock;

    @BeforeEach
    void setUp() {
        userRequestMock = UserRequest.builder()
                .name("Test")
                .email("test@test.com")
                .password("11111111")
                .exchangeCode(2)
                .build();

        userUpdateRequestMock = UserUpdateRequest.builder()
                .name("Update Test")
                .email("new_email@test.com")
                .build();

        responseMock = UserResponse.builder()
                .id(1L)
                .name("Test")
                .email("test@test.com")
                .password("11111111")
                .exchange("Mail")
                .build();

        userMock = UserEntity.builder()
                .id(1L)
                .name("Test")
                .email("test@test.com")
                .password("11111111")
                .exchange("Email")
                .build();
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Create a user")
    void shouldCreateAUser_When_CallCreateUser() {
        when(userRepository.findByEmail(userRequestMock.getEmail())).thenReturn(Optional.empty());
        when(mapper.toEntity(userRequestMock)).thenReturn(userMock);

        userMaintenanceUseCase.createUser(userRequestMock);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Update a user")
    void updateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(userMock));
        when(mapper.toUpdate(any(), any())).thenReturn(userMock);
        when(mapper.toResponse(any())).thenReturn(responseMock);

        var response = userMaintenanceUseCase.updateUser(1L, userUpdateRequestMock);
        assertNotNull(response);
    }

    @Test
    @DisplayName("USE CASE LAYER ::: Delete a user")
    void deleteUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(userMock));

        userMaintenanceUseCase.deleteUser(1L);
        verify(userRepository).delete(any(UserEntity.class));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: UserDataIntegrityException")
    void throwUserDataIntegrityException_When_CreateAUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userMock));
        assertThrows(UserDataIntegrityException.class, () -> userMaintenanceUseCase.createUser(userRequestMock));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: UserNotFoundException [UPDATE]")
    void throwUserNotFoundException_When_UpdateAUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userMaintenanceUseCase.updateUser(1L, userUpdateRequestMock));
    }

    @Test
    @DisplayName("USE CASE LAYER ::: UserNotFoundException [DELETE]")
    void throwUserNotFoundException_When_DeleteAUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userMaintenanceUseCase.deleteUser(1L));
    }

}
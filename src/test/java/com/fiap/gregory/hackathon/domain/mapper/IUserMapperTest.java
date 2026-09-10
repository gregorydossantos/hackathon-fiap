package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.UserEntity;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class IUserMapperTest {

    @Autowired
    IUserMapper mapper;

    @Test
    @DisplayName("Should returns user when call toEntity() method")
    void should_ReturnsUser_When_CallToEntityMethod() {
        var mockUserRequest = Mockito.mock(UserRequest.class);
        var user = mapper.toEntity(mockUserRequest);
        assertNotNull(user);
    }

    @Test
    @DisplayName("Should returns user response when call toResponse() method")
    void should_ReturnsUserResponse_When_CallToResponseMethod() {
        var mockUser = Mockito.mock(UserEntity.class);
        var userResponse = mapper.toResponse(mockUser);
        assertNotNull(userResponse);
    }

    @Test
    @DisplayName("Should returns user response list when call toList() response method")
    void should_ReturnsUserResponseList_When_CallToListResponseMethod() {
        var mockUser = Mockito.mock(UserEntity.class);
        var userResponses = mapper.toListResponse(List.of(mockUser));
        assertNotNull(userResponses);
    }

    @Test
    @DisplayName("Should returns user update when call toUpdate method")
    void should_ReturnsUserUpdate_When_CallToUpdateMethod() {
        var mockUserUpdateRequest = Mockito.mock(UserUpdateRequest.class);
        var mockUser = Mockito.mock(UserEntity.class);
        var userUpdate = mapper.toUpdate(mockUser, mockUserUpdateRequest);
        assertNotNull(userUpdate);
    }
}
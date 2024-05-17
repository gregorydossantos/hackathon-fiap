package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
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
    void should_ReturnsUser_When_CallToEntityMethod() {
        var mockUserRequest = Mockito.mock(UserRequest.class);
        var user = mapper.toEntity(mockUserRequest);
        assertNotNull(user);
    }

    @Test
    void should_ReturnsUserResponse_When_CallToResponseMethod() {
        var mockUser = Mockito.mock(Users.class);
        var userResponse = mapper.toResponse(mockUser);
        assertNotNull(userResponse);
    }

    @Test
    void should_ReturnsUserResponseList_When_CallToListResponseMethod() {
        var mockUser = Mockito.mock(Users.class);
        var userResponses = mapper.toListResponse(List.of(mockUser));
        assertNotNull(userResponses);
    }

    @Test
    void should_ReturnsUserUpdate_When_CallToUpdateMethod() {
        var mockUserRequest = Mockito.mock(UserRequest.class);
        var mockUser = Mockito.mock(Users.class);
        var userUpdate = mapper.toUpdate(mockUser, mockUserRequest);
        assertNotNull(userUpdate);
    }

}
package com.fiap.gregory.hackathon.rest.query.impl;

import com.fiap.gregory.hackathon.rest.dto.response.UserDataResponse;
import com.fiap.gregory.hackathon.service.query.IUserServiceQuery;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;
import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserQueryControllerImplTest {

    @LocalServerPort
    int port;

    @MockBean
    IUserServiceQuery userServiceQuery;

    UserDataResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        response = mock(UserDataResponse.class);
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should be return a HTTP status 200 - SUCCESS")
    void should_ReturnsSuccess_When_GetUsers() {
        when(userServiceQuery.getUsers(0, 10)).thenReturn(response);

        given()
                .when().get(PATH_USERS)
                .then().statusCode(HttpStatus.OK.value());

        verify(userServiceQuery).getUsers(0, 10);
    }

}
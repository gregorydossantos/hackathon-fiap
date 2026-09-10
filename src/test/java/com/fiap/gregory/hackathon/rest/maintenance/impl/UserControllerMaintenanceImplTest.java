package com.fiap.gregory.hackathon.rest.maintenance.impl;

import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.service.maintenance.IUserServiceMaintenance;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerMaintenanceImplTest {

    private static final String PATH_USERS_ID = PATH_USERS + "/" + UUID.randomUUID();

    @LocalServerPort
    int port;

    @MockBean
    IUserServiceMaintenance serviceMaintenance;

    UUID idMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssured.port = port;
        idMock = UUID.randomUUID();
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 201 - CREATED")
    void should_ReturnsHttp201_When_CreateUser() {
        var request = UserRequest.builder()
                .name("Test")
                .email("test@test.com")
                .password("Test")
                .exchangeCode(0)
                .build();
        doNothing().when(serviceMaintenance).createUser(request);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(PATH_USERS)
                .then().statusCode(HttpStatus.CREATED.value());

        verify(serviceMaintenance).createUser(any(UserRequest.class));
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 200 - SUCCESS")
    void should_ReturnsHttp200_When_UpdateUser() {
        var request = mock(UserUpdateRequest.class);
        var response = mock(UserResponse.class);
        when(serviceMaintenance.updateUser(idMock, request)).thenReturn(response);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().patch(PATH_USERS_ID)
                .then().statusCode(HttpStatus.OK.value());

        verify(serviceMaintenance).updateUser(any(UUID.class), any(UserUpdateRequest.class));
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 200 - SUCCESS")
    void should_ReturnsHttp200_When_DeleteUser() {
        doNothing().when(serviceMaintenance).deleteUser(idMock);

        given()
                .contentType(ContentType.JSON)
                .when().delete(PATH_USERS_ID)
                .then().statusCode(HttpStatus.OK.value());

        verify(serviceMaintenance).deleteUser(any(UUID.class));
    }
}
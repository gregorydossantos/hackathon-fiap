package com.fiap.gregory.hackathon.rest.maintenance.impl;

import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.service.maintenance.IUserServiceMaintenance;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserMaintenanceControllerImplTest {

    private static final String PATH_USERS_ID = PATH_USERS + "/1";

    @LocalServerPort
    int port;

    @MockBean
    IUserServiceMaintenance serviceMaintenance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssured.port = port;
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 201 - CREATED")
    void should_ReturnsHttp201_When_CreateUser() {
        var request = Mockito.mock(UserRequest.class);
        doNothing().when(serviceMaintenance).createUser(any(UserRequest.class));

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
        var request = Mockito.mock(UserRequest.class);
        var response = Mockito.mock(UserResponse.class);
        when(serviceMaintenance.updateUser(1L, request)).thenReturn(response);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(PATH_USERS_ID)
                .then().statusCode(HttpStatus.OK.value());

        verify(serviceMaintenance).updateUser(anyLong(), any(UserRequest.class));
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 200 - SUCCESS")
    void should_ReturnsHttp200_When_DeleteUser() {
        doNothing().when(serviceMaintenance).deleteUser(anyLong());

        given()
                .contentType(ContentType.JSON)
                .when().post(PATH_USERS_ID)
                .then().statusCode(HttpStatus.OK.value());

        verify(serviceMaintenance).deleteUser(anyLong());
    }
}
package com.fiap.gregory.hackathon.rest.query.impl;

import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.query.IUserQueryController;
import com.fiap.gregory.hackathon.service.query.IUserServiceQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.PATH_USERS;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = PATH_USERS, produces = {"application/json"})
public class UserQueryControllerImpl implements IUserQueryController {

    IUserServiceQuery userService;

    @Operation(summary = "Get a list of Users", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of users"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @Override
    public ResponseEntity<List<UserResponse>> getUsers() {
        var response = userService.getUsers();
        return ResponseEntity.ok().body(response);
    }

}

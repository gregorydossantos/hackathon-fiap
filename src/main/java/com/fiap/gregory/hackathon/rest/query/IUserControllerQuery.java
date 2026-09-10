package com.fiap.gregory.hackathon.rest.query;

import com.fiap.gregory.hackathon.rest.dto.response.UserDataResponse;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserControllerQuery {
    @GetMapping
    ResponseEntity<UserDataResponse> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size);
}

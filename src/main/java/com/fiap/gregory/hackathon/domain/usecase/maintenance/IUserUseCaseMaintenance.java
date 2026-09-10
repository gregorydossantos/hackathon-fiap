package com.fiap.gregory.hackathon.domain.usecase.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

import java.util.UUID;

public interface IUserUseCaseMaintenance {
    void createUser(UserRequest request);

    UserResponse updateUser(UUID id, UserUpdateRequest request);

    void deleteUser(UUID id);
}

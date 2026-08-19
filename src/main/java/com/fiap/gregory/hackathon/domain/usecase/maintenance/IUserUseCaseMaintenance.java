package com.fiap.gregory.hackathon.domain.usecase.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

public interface IUserUseCaseMaintenance {

    void createUser(UserRequest request);

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);

}

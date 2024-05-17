package com.fiap.gregory.hackathon.domain.usecase.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

public interface IUserMaintenanceUseCase {

    void createUser(UserRequest request);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);

}

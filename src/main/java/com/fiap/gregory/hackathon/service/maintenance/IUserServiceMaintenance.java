package com.fiap.gregory.hackathon.service.maintenance;

import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

public interface IUserServiceMaintenance {

    void createUser(UserRequest request);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);

}

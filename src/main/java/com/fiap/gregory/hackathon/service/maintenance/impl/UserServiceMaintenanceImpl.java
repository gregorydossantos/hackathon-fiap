package com.fiap.gregory.hackathon.service.maintenance.impl;

import com.fiap.gregory.hackathon.domain.usecase.maintenance.IUserMaintenanceUseCase;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.service.maintenance.IUserServiceMaintenance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceMaintenanceImpl implements IUserServiceMaintenance {

    IUserMaintenanceUseCase userMaintenanceUseCase;

    @Override
    public void createUser(UserRequest request) {
        userMaintenanceUseCase.createUser(request);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        return userMaintenanceUseCase.updateUser(id, request);
    }

    @Override
    public void deleteUser(Long id) {
        userMaintenanceUseCase.deleteUser(id);
    }
}

package com.fiap.gregory.hackathon.service.query.impl;

import com.fiap.gregory.hackathon.domain.usecase.query.IUserQueryUseCase;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.service.query.IUserServiceQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceQueryImpl implements IUserServiceQuery {

    IUserQueryUseCase userQueryUseCase;

    @Override
    public List<UserResponse> getUsers() {
        return userQueryUseCase.getUsers();
    }

}

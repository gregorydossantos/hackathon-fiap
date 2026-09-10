package com.fiap.gregory.hackathon.service.query.impl;

import com.fiap.gregory.hackathon.domain.usecase.query.IUserUseCaseQuery;
import com.fiap.gregory.hackathon.rest.dto.response.UserDataResponse;
import com.fiap.gregory.hackathon.service.query.IUserServiceQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceQueryImpl implements IUserServiceQuery {

    IUserUseCaseQuery userQueryUseCase;

    @Override
    public UserDataResponse getUsers(int page, int size) {
        return userQueryUseCase.getUsers(page, size);
    }
}

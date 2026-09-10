package com.fiap.gregory.hackathon.domain.usecase.query;

import com.fiap.gregory.hackathon.rest.dto.response.UserDataResponse;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

import java.util.List;

public interface IUserUseCaseQuery {
    UserDataResponse getUsers(int page, int size);
}

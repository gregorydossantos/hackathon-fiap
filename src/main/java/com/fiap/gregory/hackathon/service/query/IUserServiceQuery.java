package com.fiap.gregory.hackathon.service.query;

import com.fiap.gregory.hackathon.rest.dto.response.UserDataResponse;

public interface IUserServiceQuery {
    UserDataResponse getUsers(int page, int size);
}

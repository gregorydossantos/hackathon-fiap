package com.fiap.gregory.hackathon.service.query;

import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;

import java.util.List;

public interface IUserServiceQuery {

    List<UserResponse> getUsers();

}

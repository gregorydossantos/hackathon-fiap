package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    Users toEntity(UserRequest request);

    UserResponse toResponse(Users user);

    List<UserResponse> toListResponse(List<Users> users);

    @Mapping(target = "id", ignore = true)
    Users toUpdate(@MappingTarget Users user, UserRequest request);

}

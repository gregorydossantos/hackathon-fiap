package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    Users toEntity(UserRequest request);

    UserResponse toResponse(Users user);

    List<UserResponse> toListResponse(List<Users> users);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users toUpdate(@MappingTarget Users user, UserUpdateRequest request);

}

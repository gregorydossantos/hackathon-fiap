package com.fiap.gregory.hackathon.domain.mapper;

import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

import static com.fiap.gregory.hackathon.infra.db.enums.ExchangeType.getDescriptionByCode;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    @Mapping(source = "exchangeCode", target = "exchange", qualifiedByName = "getDescription")
    Users toEntity(UserRequest request);

    UserResponse toResponse(Users user);

    List<UserResponse> toListResponse(List<Users> users);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users toUpdate(@MappingTarget Users user, UserUpdateRequest request);

    @Named("getDescription")
    static String getDescription(int code) {
        return getDescriptionByCode(code);
    }
}
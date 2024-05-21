package com.fiap.gregory.hackathon.domain.usecase.query.impl;

import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.domain.usecase.query.IUserUseCaseQuery;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_NOT_FOUND;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserUseCaseQueryImpl implements IUserUseCaseQuery {

    IUserRepository userRepository;
    IUserMapper userMapper;

    @Override
    public List<UserResponse> getUsers() {
        var users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        return userMapper.toListResponse(users);
    }

}

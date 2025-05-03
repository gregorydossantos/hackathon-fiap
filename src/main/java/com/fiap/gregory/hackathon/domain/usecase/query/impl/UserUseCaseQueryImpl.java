package com.fiap.gregory.hackathon.domain.usecase.query.impl;

import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.domain.usecase.query.IUserUseCaseQuery;
import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import com.fiap.gregory.hackathon.service.encryption.IEncryptionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserUseCaseQueryImpl implements IUserUseCaseQuery {

    IUserRepository userRepository;
    IUserMapper userMapper;
    IEncryptionService encryptionService;

    @Override
    public List<UserResponse> getUsers() {
        log.info("Get all user from database");
        var users = userRepository.findAll();

        log.info("Validate if exists Users");
        if (users.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        log.info("Decrypting password field from all users");
        decryptingAllPasswords(users);

        log.info("Return all Users");
        return userMapper.toListResponse(users);
    }

    private void decryptingAllPasswords(List<Users> users) {
        for (Users user : users) {
            log.debug("Encrypt password: {}", user.getPassword());
            user.setPassword(encryptionService.decrypt(user.getPassword()));
            log.debug("Decrypt password: {}", user.getPassword());
        }
    }

}

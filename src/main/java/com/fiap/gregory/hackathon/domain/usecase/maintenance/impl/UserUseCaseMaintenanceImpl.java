package com.fiap.gregory.hackathon.domain.usecase.maintenance.impl;

import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.domain.usecase.maintenance.IUserUseCaseMaintenance;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.request.UserUpdateRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import com.fiap.gregory.hackathon.service.encryption.IEncryptionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.EMAIL_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_NOT_FOUND;
import static com.fiap.gregory.hackathon.useful.StringUseful.nonNullOrEmpty;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserUseCaseMaintenanceImpl implements IUserUseCaseMaintenance {

    IUserRepository userRepository;
    IUserMapper mapper;
    IEncryptionService encryptionService;

    @Override
    public void createUser(UserRequest request) {
        log.info("====[CREATE USER]====");
        log.info("Validate if user already exists by email {}", request.getEmail());
        if (userExists(request.getEmail())) {
            throw new UserDataIntegrityException(EMAIL_ALREADY_REGISTER);
        }

        log.info("[CREATE] ==== Initialize encryption password field");
        encryptPassword(request);

        log.info("Convert request in entity");
        var user = mapper.toEntity(request);

        log.info("Persist entity at database: {}", user);
        user.setUserId(UUID.randomUUID());
        userRepository.save(user);
    }

    @Override
    public UserResponse updateUser(UUID id, UserUpdateRequest request) {
        log.info("====[UPDATE USER]====");
        log.info("Get register by ID {} in database", id);
        var oldUser = userRepository.findByUserId(id);

        log.info("[UPDATE] ==== Validating if exists register on database");
        if (oldUser.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        log.info("[UPDATE] ==== Update encryption password field");
        if (nonNullOrEmpty(request.getPassword())) {
            request.setPassword(encryptionService.encrypt(request.getPassword()));
        }

        log.info("Update old user");
        var userUpdate = mapper.toUpdate(oldUser.get(), request);
        log.info("Saving updated user: {}", userUpdate);
        userRepository.save(userUpdate);

        return mapper.toResponse(userUpdate);
    }

    @Override
    public void deleteUser(UUID id) {
        log.info("====[DELETE USER]====");
        log.info("Get user by ID {}", id);
        var user = userRepository.findByUserId(id);

        log.info("[DELETE] ==== Validating if exists register on database");
        if (user.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        log.info("Deleting user from database");
        userRepository.delete(user.get());
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private void encryptPassword(UserRequest request) {
        log.info("Encrypting user password");
        request.setPassword(encryptionService.encrypt(request.getPassword()));
    }
}

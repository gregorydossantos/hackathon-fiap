package com.fiap.gregory.hackathon.domain.usecase.maintenance.impl;

import com.fiap.gregory.hackathon.domain.mapper.IUserMapper;
import com.fiap.gregory.hackathon.domain.usecase.maintenance.IUserUseCaseMaintenance;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.request.UserRequest;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserDataIntegrityException;
import com.fiap.gregory.hackathon.rest.exceptionhandler.exception.UserNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_ALREADY_REGISTER;
import static com.fiap.gregory.hackathon.domain.message.UserMessage.USER_NOT_FOUND;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserUseCaseMaintenanceImpl implements IUserUseCaseMaintenance {

    IUserRepository userRepository;
    IUserMapper mapper;

    @Override
    public void createUser(UserRequest request) {
        if (userExists(request.getEmail())) {
            throw new UserDataIntegrityException(USER_ALREADY_REGISTER);
        }

        var user = mapper.toEntity(request);
        userRepository.save(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        var oldUser = userRepository.findById(id);
        if (oldUser.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        var userUpdate = mapper.toUpdate(oldUser.get(), request);
        userRepository.save(userUpdate);

        return mapper.toResponse(userUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        userRepository.delete(user.get());
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}

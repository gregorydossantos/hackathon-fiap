package com.fiap.gregory.hackathon.infra.db.repository;

import com.fiap.gregory.hackathon.infra.db.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String email);

    Optional<UserEntity> findByEmail(String email);
}

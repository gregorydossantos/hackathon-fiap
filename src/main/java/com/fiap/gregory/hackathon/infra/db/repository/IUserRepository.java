package com.fiap.gregory.hackathon.infra.db.repository;

import com.fiap.gregory.hackathon.infra.db.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

}

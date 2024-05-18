package com.fiap.gregory.hackathon.infra.db.repository;

import com.fiap.gregory.hackathon.infra.db.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends JpaRepository<Games, Long> {
}

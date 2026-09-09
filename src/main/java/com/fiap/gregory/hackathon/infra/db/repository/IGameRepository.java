package com.fiap.gregory.hackathon.infra.db.repository;

import com.fiap.gregory.hackathon.infra.db.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IGameRepository extends JpaRepository<GameEntity, Long>, PagingAndSortingRepository<GameEntity, Long> {
    Optional<GameEntity> findByGameId(UUID value);

    Optional<GameEntity> findByName(String value);
}

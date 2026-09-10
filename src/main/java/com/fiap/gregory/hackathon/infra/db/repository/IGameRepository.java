package com.fiap.gregory.hackathon.infra.db.repository;

import com.fiap.gregory.hackathon.infra.db.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGameRepository extends JpaRepository<GameEntity, Long>, PagingAndSortingRepository<GameEntity, Long> {
    Optional<GameEntity> findByGameId(String value);

    Optional<GameEntity> findByNameAndBrand(String name, String brand);
}

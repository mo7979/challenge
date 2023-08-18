package com.example.challenge.repository.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.challenge.entities.RecordEntity;

public interface RecordRepository extends JpaRepository<com.example.challenge.entities.RecordEntity, Long> {
	Optional<RecordEntity> findByUserId(Long userId);
	Optional<RecordEntity> findTopByOrderByDateDesc();
}


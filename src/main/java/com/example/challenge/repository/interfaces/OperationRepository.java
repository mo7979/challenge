package com.example.challenge.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.challenge.entities.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    OperationEntity findByTypeEquals(String type);
    // Puedes agregar otros métodos de consulta si son necesarios
}

package com.example.challenge.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.challenge.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}

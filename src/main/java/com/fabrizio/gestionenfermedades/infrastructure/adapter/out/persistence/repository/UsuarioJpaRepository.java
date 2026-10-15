package com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.repository;

import com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.entity.UsuarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity, String> {

    Optional<UsuarioJpaEntity> findByEmail(String email);
}
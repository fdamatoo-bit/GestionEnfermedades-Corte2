package com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.repository;

import com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.entity.EnfermedadJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnfermedadJpaRepository extends JpaRepository<EnfermedadJpaEntity, Long> {

    List<EnfermedadJpaEntity> findByNivelGravedadIgnoreCase(String nivelGravedad);
}
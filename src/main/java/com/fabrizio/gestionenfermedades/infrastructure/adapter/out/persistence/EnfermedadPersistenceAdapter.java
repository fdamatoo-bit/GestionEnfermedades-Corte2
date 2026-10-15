package com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence;

import com.fabrizio.gestionenfermedades.domain.model.Enfermedad;
import com.fabrizio.gestionenfermedades.domain.port.out.EnfermedadRepositoryPort;
import com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.entity.EnfermedadJpaEntity;
import com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.repository.EnfermedadJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnfermedadPersistenceAdapter implements EnfermedadRepositoryPort {

    private final EnfermedadJpaRepository repository;

    public EnfermedadPersistenceAdapter(EnfermedadJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Enfermedad guardar(Enfermedad enfermedad) {
        EnfermedadJpaEntity entity = convertirAEntity(enfermedad);
        return convertirADominio(repository.save(entity));
    }

    @Override
    public Optional<Enfermedad> buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::convertirADominio);
    }

    @Override
    public List<Enfermedad> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::convertirADominio)
                .toList();
    }

    @Override
    public List<Enfermedad> buscarPorNivelGravedad(String nivelGravedad) {
        return repository.findByNivelGravedadIgnoreCase(nivelGravedad)
                .stream()
                .map(this::convertirADominio)
                .toList();
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    private EnfermedadJpaEntity convertirAEntity(Enfermedad enfermedad) {
        return new EnfermedadJpaEntity(
                enfermedad.getId(),
                enfermedad.getNombre(),
                enfermedad.getNombreCientifico(),
                enfermedad.getNivelGravedad(),
                enfermedad.getSintomas(),
                enfermedad.getMedicamentos(),
                enfermedad.isEsContagiosa(),
                enfermedad.isEsCubiertaPorPost(),
                enfermedad.isRequiereIncapacidad()
        );
    }

    private Enfermedad convertirADominio(EnfermedadJpaEntity entity) {
        return new Enfermedad(
                entity.getId(),
                entity.getNombre(),
                entity.getNombreCientifico(),
                entity.getNivelGravedad(),
                entity.getSintomas(),
                entity.getMedicamentos(),
                entity.isEsContagiosa(),
                entity.isEsCubiertaPorPost(),
                entity.isRequiereIncapacidad()
        );
    }
}
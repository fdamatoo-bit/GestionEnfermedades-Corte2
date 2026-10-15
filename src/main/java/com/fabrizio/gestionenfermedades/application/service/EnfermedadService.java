package com.fabrizio.gestionenfermedades.application.service;

import com.fabrizio.gestionenfermedades.domain.model.Enfermedad;
import com.fabrizio.gestionenfermedades.domain.port.in.EnfermedadUseCase;
import com.fabrizio.gestionenfermedades.domain.port.out.EnfermedadRepositoryPort;

import java.util.List;
import java.util.Optional;

public class EnfermedadService implements EnfermedadUseCase {

    private final EnfermedadRepositoryPort enfermedadRepositoryPort;

    public EnfermedadService(EnfermedadRepositoryPort enfermedadRepositoryPort) {
        this.enfermedadRepositoryPort = enfermedadRepositoryPort;
    }

    @Override
    public Enfermedad crear(Enfermedad enfermedad) {
        return enfermedadRepositoryPort.guardar(enfermedad);
    }

    @Override
    public Optional<Enfermedad> buscarPorId(Long id) {
        return enfermedadRepositoryPort.buscarPorId(id);
    }

    @Override
    public List<Enfermedad> listarTodas() {
        return enfermedadRepositoryPort.listarTodas();
    }

    @Override
    public List<Enfermedad> buscarPorNivelGravedad(String nivelGravedad) {
        return enfermedadRepositoryPort.buscarPorNivelGravedad(nivelGravedad);
    }

    @Override
    public Enfermedad actualizar(Long id, Enfermedad enfermedad) {
        if (enfermedadRepositoryPort.buscarPorId(id).isEmpty()) {
            throw new IllegalArgumentException("Enfermedad no encontrada");
        }

        enfermedad.setId(id);
        return enfermedadRepositoryPort.guardar(enfermedad);
    }

    @Override
    public void eliminar(Long id) {
        enfermedadRepositoryPort.eliminarPorId(id);
    }
}
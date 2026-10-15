package com.fabrizio.gestionenfermedades.domain.port.out;

import com.fabrizio.gestionenfermedades.domain.model.Enfermedad;

import java.util.List;
import java.util.Optional;

public interface EnfermedadRepositoryPort {

    Enfermedad guardar(Enfermedad enfermedad);

    Optional<Enfermedad> buscarPorId(Long id);

    List<Enfermedad> listarTodas();

    List<Enfermedad> buscarPorNivelGravedad(String nivelGravedad);

    void eliminarPorId(Long id);
}
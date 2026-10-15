package com.fabrizio.gestionenfermedades.domain.port.in;

import com.fabrizio.gestionenfermedades.domain.model.Enfermedad;

import java.util.List;
import java.util.Optional;

public interface EnfermedadUseCase {

    Enfermedad crear(Enfermedad enfermedad);

    Optional<Enfermedad> buscarPorId(Long id);

    List<Enfermedad> listarTodas();

    List<Enfermedad> buscarPorNivelGravedad(String nivelGravedad);

    Enfermedad actualizar(Long id, Enfermedad enfermedad);

    void eliminar(Long id);
}
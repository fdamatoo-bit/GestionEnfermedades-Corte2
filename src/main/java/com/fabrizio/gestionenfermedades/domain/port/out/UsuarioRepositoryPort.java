package com.fabrizio.gestionenfermedades.domain.port.out;

import com.fabrizio.gestionenfermedades.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorUsername(String username);

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> listarTodos();

    void eliminarPorUsername(String username);
}

package com.fabrizio.gestionenfermedades.domain.port.in;

import com.fabrizio.gestionenfermedades.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioUseCase {

    Usuario crear(Usuario usuario);

    Optional<Usuario> buscarPorUsername(String username);

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> listarTodos();

    Usuario actualizar(String username, Usuario usuario);

    void eliminar(String username);
}
package com.fabrizio.gestionenfermedades.application.service;

import com.fabrizio.gestionenfermedades.domain.model.Usuario;
import com.fabrizio.gestionenfermedades.domain.port.in.UsuarioUseCase;
import com.fabrizio.gestionenfermedades.domain.port.out.UsuarioRepositoryPort;

import java.util.List;
import java.util.Optional;

public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario crear(Usuario usuario) {

        if (usuario.getUsername() != null) {
            usuario.setUsername(usuario.getUsername().trim());
        }

        if (usuario.getPassword() != null) {
            usuario.setPassword(usuario.getPassword().trim());
        }

        if (usuario.getNombre() != null) {
            usuario.setNombre(usuario.getNombre().trim());
        }

        if (usuario.getEmail() != null) {
            usuario.setEmail(usuario.getEmail().trim());
        }

        return usuarioRepositoryPort.guardar(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepositoryPort.buscarPorUsername(username);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepositoryPort.buscarPorEmail(email);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepositoryPort.listarTodos();
    }

    @Override
    public Usuario actualizar(String username, Usuario usuario) {
        if (usuarioRepositoryPort.buscarPorUsername(username).isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        usuario.setUsername(username);
        return usuarioRepositoryPort.guardar(usuario);
    }

    @Override
    public void eliminar(String username) {
        usuarioRepositoryPort.eliminarPorUsername(username);
    }
}
package com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence;

import com.fabrizio.gestionenfermedades.domain.model.Usuario;
import com.fabrizio.gestionenfermedades.domain.port.out.UsuarioRepositoryPort;
import com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.entity.UsuarioJpaEntity;
import com.fabrizio.gestionenfermedades.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository repository;

    public UsuarioPersistenceAdapter(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioJpaEntity entity = convertirAEntity(usuario);
        return convertirADominio(repository.save(entity));
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return repository.findById(username)
                .map(this::convertirADominio);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(this::convertirADominio);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::convertirADominio)
                .toList();
    }

    @Override
    public void eliminarPorUsername(String username) {
        repository.deleteById(username);
    }

    private UsuarioJpaEntity convertirAEntity(Usuario usuario) {
        return new UsuarioJpaEntity(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }

    private Usuario convertirADominio(UsuarioJpaEntity entity) {
        return new Usuario(
                entity.getUsername(),
                entity.getPassword(),
                entity.getNombre(),
                entity.getEmail()
        );
    }
}
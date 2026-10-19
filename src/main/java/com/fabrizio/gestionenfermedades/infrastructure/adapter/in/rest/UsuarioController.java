package com.fabrizio.gestionenfermedades.infrastructure.adapter.in.rest;

import com.fabrizio.gestionenfermedades.domain.model.Usuario;
import com.fabrizio.gestionenfermedades.domain.port.in.UsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario creado = usuarioUseCase.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioUseCase.listarTodos());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Usuario> buscarPorUsername(
            @PathVariable String username) {

        return usuarioUseCase.buscarPorUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<Usuario> buscarPorEmail(
            @RequestParam String email) {

        return usuarioUseCase.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{username}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable String username,
            @RequestBody Usuario usuario) {

        try {
            return ResponseEntity.ok(
                    usuarioUseCase.actualizar(username, usuario)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> eliminar(
            @PathVariable String username) {

        if (usuarioUseCase.buscarPorUsername(username).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioUseCase.eliminar(username);
        return ResponseEntity.noContent().build();
    }

        // REPORTE PARAMETRIZADO 1:
    // Busca usuarios cuyo nombre contenga el texto enviado
    @GetMapping("/reportes/nombre")
    public ResponseEntity<List<Usuario>> reportePorNombre(
            @RequestParam String nombre) {

        List<Usuario> resultado = usuarioUseCase.listarTodos()
                .stream()
                .filter(usuario ->
                        usuario.getNombre() != null &&
                        usuario.getNombre()
                                .toLowerCase()
                                .contains(nombre.toLowerCase()))
                .toList();

        return ResponseEntity.ok(resultado);
    }

    // REPORTE PARAMETRIZADO 2:
    // Busca usuarios cuyo correo contenga el texto enviado
    @GetMapping("/reportes/email")
    public ResponseEntity<List<Usuario>> reportePorEmail(
            @RequestParam String email) {

        List<Usuario> resultado = usuarioUseCase.listarTodos()
                .stream()
                .filter(usuario ->
                        usuario.getEmail() != null &&
                        usuario.getEmail()
                                .toLowerCase()
                                .contains(email.toLowerCase()))
                .toList();

        return ResponseEntity.ok(resultado);
    }
}
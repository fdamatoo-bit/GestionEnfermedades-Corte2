package com.fabrizio.gestionenfermedades.infrastructure.adapter.in.rest;

import com.fabrizio.gestionenfermedades.domain.model.Usuario;
import com.fabrizio.gestionenfermedades.domain.port.in.UsuarioUseCase;
import com.fabrizio.gestionenfermedades.infrastructure.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioUseCase usuarioUseCase;
    private final JwtService jwtService;

    public AuthController(
            UsuarioUseCase usuarioUseCase,
            JwtService jwtService) {

        this.usuarioUseCase = usuarioUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> datos) {

        String username = datos.get("username");
        String password = datos.get("password");

        Optional<Usuario> usuario =
                usuarioUseCase.buscarPorUsername(username);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(Map.of(
                            "mensaje",
                            "Usuario o contraseña incorrectos"
                    ));
        }

        if (!usuario.get().getPassword().equals(password)) {
            return ResponseEntity.status(401)
                    .body(Map.of(
                            "mensaje",
                            "Usuario o contraseña incorrectos"
                    ));
        }

        String token = jwtService.generarToken(username);

        return ResponseEntity.ok(
                Map.of(
                        "username", username,
                        "token", token
                )
        );
    }
}
package com.fabrizio.gestionenfermedades.infrastructure.adapter.in.rest;

import com.fabrizio.gestionenfermedades.domain.model.Usuario;
import com.fabrizio.gestionenfermedades.domain.port.in.UsuarioUseCase;
import com.fabrizio.gestionenfermedades.infrastructure.email.EmailService;
import com.fabrizio.gestionenfermedades.infrastructure.security.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioUseCase usuarioUseCase;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AuthController(
            UsuarioUseCase usuarioUseCase,
            JwtService jwtService,
            EmailService emailService) {

        this.usuarioUseCase = usuarioUseCase;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {

        String username = credenciales.get("username");
        String password = credenciales.get("password");

        Optional<Usuario> usuarioOptional =
                usuarioUseCase.buscarPorUsername(username);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "mensaje",
                            "Usuario o contraseña incorrectos"
                    ));
        }

        Usuario usuario = usuarioOptional.get();

        if (!usuario.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "mensaje",
                            "Usuario o contraseña incorrectos"
                    ));
        }

        String token = jwtService.generarToken(usuario.getUsername());

        return ResponseEntity.ok(
                Map.of(
                        "username", usuario.getUsername(),
                        "token", token
                )
        );
    }

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperarClave(
            @RequestBody Map<String, String> datos) {

        String email = datos.get("email");

        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "mensaje",
                            "Debe proporcionar un correo electrónico"
                    ));
        }

        Optional<Usuario> usuarioOptional =
                usuarioUseCase.buscarPorEmail(email);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "mensaje",
                            "No existe un usuario registrado con ese correo"
                    ));
        }

        Usuario usuario = usuarioOptional.get();

        emailService.enviarRecordatorioClave(
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getPassword()
        );

        return ResponseEntity.ok(
                Map.of(
                        "mensaje",
                        "El recordatorio de clave fue enviado al correo registrado"
                )
        );
    }
}
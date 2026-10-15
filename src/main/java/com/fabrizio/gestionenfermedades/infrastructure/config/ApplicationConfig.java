package com.fabrizio.gestionenfermedades.infrastructure.config;

import com.fabrizio.gestionenfermedades.application.service.EnfermedadService;
import com.fabrizio.gestionenfermedades.application.service.UsuarioService;
import com.fabrizio.gestionenfermedades.domain.port.in.EnfermedadUseCase;
import com.fabrizio.gestionenfermedades.domain.port.in.UsuarioUseCase;
import com.fabrizio.gestionenfermedades.domain.port.out.EnfermedadRepositoryPort;
import com.fabrizio.gestionenfermedades.domain.port.out.UsuarioRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UsuarioUseCase usuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new UsuarioService(usuarioRepositoryPort);
    }

    @Bean
    public EnfermedadUseCase enfermedadUseCase(
            EnfermedadRepositoryPort enfermedadRepositoryPort) {

        return new EnfermedadService(enfermedadRepositoryPort);
    }
}
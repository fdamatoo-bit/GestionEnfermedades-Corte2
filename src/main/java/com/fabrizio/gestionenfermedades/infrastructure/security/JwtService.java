package com.fabrizio.gestionenfermedades.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationMs;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-ms}") long expirationMs) {

        this.key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

        this.expirationMs = expirationMs;
    }

    public String generarToken(String username) {

        Date ahora = new Date();
        Date expiracion = new Date(
                ahora.getTime() + expirationMs
        );

        return Jwts.builder()
                .subject(username)
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(key)
                .compact();
    }
}
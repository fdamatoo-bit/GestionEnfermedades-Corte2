package com.fabrizio.gestionenfermedades.infrastructure.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private final String brevoApiKey;
    private final String senderEmail;
    private final RestClient restClient;

    public EmailService(
            @Value("${brevo.api-key}") String brevoApiKey,
            @Value("${brevo.sender-email}") String senderEmail) {

        this.brevoApiKey = brevoApiKey;
        this.senderEmail = senderEmail;

        this.restClient = RestClient.builder()
                .baseUrl("https://api.brevo.com")
                .build();
    }

    public void enviarRecordatorioClave(
            String destinatario,
            String nombre,
            String password) {

        Map<String, Object> cuerpo = Map.of(
                "sender", Map.of(
                        "name", "Gestion de Enfermedades",
                        "email", senderEmail
                ),
                "to", List.of(
                        Map.of(
                                "email", destinatario,
                                "name", nombre
                        )
                ),
                "subject", "Recordatorio de clave - Gestion de Enfermedades",
                "textContent",
                "Hola " + nombre + ",\n\n" +
                "Se ha solicitado un recordatorio de tu clave.\n\n" +
                "Tu clave registrada es: " + password + "\n\n" +
                "Gestion de Enfermedades\n" +
                "Fabrizio Antonio D'Amato Osorio"
        );

        restClient.post()
                .uri("/v3/smtp/email")
                .header("api-key", brevoApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cuerpo)
                .retrieve()
                .toBodilessEntity();
    }
}
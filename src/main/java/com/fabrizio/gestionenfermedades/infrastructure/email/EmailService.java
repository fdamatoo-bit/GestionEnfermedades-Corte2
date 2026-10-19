package com.fabrizio.gestionenfermedades.infrastructure.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarRecordatorioClave(String destinatario, String nombre, String password) {

        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setTo(destinatario);
        mensaje.setSubject("Recordatorio de clave - Gestion de Enfermedades");

        mensaje.setText(
                "Hola " + nombre + ",\n\n" +
                "Se ha solicitado un recordatorio de tu clave.\n\n" +
                "Tu clave registrada es: " + password + "\n\n" +
                "Gestion de Enfermedades\n" +
                "Fabrizio Antonio D'Amato Osorio"
        );

        mailSender.send(mensaje);
    }
}
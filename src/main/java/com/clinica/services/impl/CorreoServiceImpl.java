package com.clinica.services.impl;

import com.clinica.services.CorreoService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de envío de correos electrónicos.
 */
@Service
public class CorreoServiceImpl implements CorreoService {

    private final JavaMailSender mailSender;

    public CorreoServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarCorreoActivacion(String email, String token) {
        String link = "http://localhost:80/activar-cuenta?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Activa tu cuenta en BodhiWellness");
        message.setText("Bienvenido a BodhiWellness.\n\n"
                + "Por favor, activa tu cuenta haciendo clic en el siguiente enlace:\n"
                + link + "\n\nGracias por unirte a nuestra plataforma.");
        mailSender.send(message);
    }

    @Override
    public void enviarCorreoRecuperacion(String email, String token) {
        String link = token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recupera tu contraseña en BodhiWellness");
        message.setText("Hemos recibido una solicitud para restablecer tu contraseña.\n\n"
                + "Por favor, restablece tu contraseña haciendo clic en el siguiente enlace:\n"
                + link + "\n\nSi no solicitaste este cambio, ignora este mensaje.");
        mailSender.send(message);
    }
}

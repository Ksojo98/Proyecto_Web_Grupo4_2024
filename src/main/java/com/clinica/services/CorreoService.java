package com.clinica.services;

/**
 * Interfaz para el servicio de envío de correos electrónicos.
 * @author Andgr
 */
public interface CorreoService {
    
    /**
     * Envía un correo de activación de cuenta.
     * @param email Correo electrónico del destinatario.
     * @param token Token único para activar la cuenta.
     */
    void enviarCorreoActivacion(String email, String token);

    /**
     * Envía un correo de recuperación de contraseña.
     * @param email Correo electrónico del destinatario.
     * @param token Token único para restablecer la contraseña.
     */
    void enviarCorreoRecuperacion(String email, String token);
}

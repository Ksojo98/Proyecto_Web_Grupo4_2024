package com.clinica.services;

import com.clinica.domain.Usuario;

public interface UsuarioService {
    void registrar(Usuario usuario);
    boolean activarCuenta(String token); // Método para activar la cuenta

    // Métodos nuevos
    boolean enviarEnlaceRecuperacion(String correo); // Para enviar el enlace de recuperación
    boolean restablecerContrasena(String token, String nuevaContrasena); // Para restablecer la contraseña
    boolean verificarTokenRecuperacion(String token); // Para verificar si el token de recuperación es válido
}

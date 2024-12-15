package com.clinica.controller;

import com.clinica.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para manejar la activación de cuentas de usuario.
 */
@Controller
public class ActivacionCuentaController {

    private final UsuarioService usuarioService;

    // Constructor para inyectar dependencias
    public ActivacionCuentaController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para activar la cuenta del usuario.
     * 
     * @param token El token de activación recibido en el correo.
     * @param model Modelo para pasar datos a la vista.
     * @return La vista correspondiente según el resultado de la activación.
     */
    @GetMapping("/activar-cuenta")
    public String activarCuenta(@RequestParam("token") String token, Model model) {
        boolean cuentaActivada = usuarioService.activarCuenta(token);

        if (cuentaActivada) {
            model.addAttribute("mensaje", "¡Tu cuenta ha sido activada con éxito!");
            return "activacion/exitosa"; // Vista de éxito dentro de la carpeta "activacion"
        } else {
            model.addAttribute("mensaje", "El enlace de activación no es válido o ha expirado.");
            return "activacion/fallida"; // Vista de error dentro de la carpeta "activacion"
        }
    }
}

package com.clinica.controller;

import com.clinica.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecuperacionController {

    private final UsuarioService usuarioService;

    public RecuperacionController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/recuperar")
    public String mostrarFormularioRecuperar() {
        return "login/recuperar"; // Vista del formulario de recuperación
    }

    @PostMapping("/recuperar")
    public String enviarEnlaceRecuperacion(@RequestParam("correo") String correo, Model model) {
        boolean enviado = usuarioService.enviarEnlaceRecuperacion(correo);

        if (enviado) {
            model.addAttribute("mensaje", "Se ha enviado un enlace de recuperación a tu correo.");
            return "login/recuperacion-enviada"; // Vista de confirmación
        } else {
            model.addAttribute("error", "El correo ingresado no está registrado.");
            return "login/recuperar"; // Vuelve al formulario con un mensaje de error
        }
    }

    @GetMapping("/cambiar-contrasena")
    public String mostrarFormularioCambio(@RequestParam("token") String token, Model model) {
        // Verifica si el token es válido
        if (usuarioService.verificarTokenRecuperacion(token)) {
            model.addAttribute("token", token); // Pasar el token a la vista
            return "login/cambiar-contrasena"; // Formulario para cambiar la contraseña
        } else {
            model.addAttribute("error", "El enlace de recuperación no es válido o ha expirado.");
            return "login/recuperar"; // Redirigir a la página de recuperación
        }
    }

    @PostMapping("/cambiar-contrasena")
    public String cambiarContrasena(@RequestParam("token") String token,
                                    @RequestParam("nuevaContrasena") String nuevaContrasena,
                                    Model model) {
        boolean cambiada = usuarioService.restablecerContrasena(token, nuevaContrasena);

        if (cambiada) {
            model.addAttribute("mensaje", "¡Tu contraseña ha sido cambiada con éxito!");
            return "login/cambio-exitoso"; // Vista de confirmación
        } else {
            model.addAttribute("error", "El enlace de recuperación no es válido o ha expirado.");
            return "login/recuperar"; // Redirigir a la página de recuperación
        }
    }
}
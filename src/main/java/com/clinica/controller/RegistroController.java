package com.clinica.controller;

import com.clinica.domain.Usuario;
import com.clinica.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    // Constructor para inyectar el servicio
    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro/registro"; // Cambiado para incluir la carpeta
    }

    // Procesa el formulario de registro
    @PostMapping("/registro")
    public String registrarUsuario(
            @Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            RedirectAttributes redirectAttrs) {

        // Verifica si hay errores en el formulario
        if (result.hasErrors()) {
            return "registro"; // Devuelve el formulario con los errores
        }

        try {
            usuarioService.registrar(usuario); // Llama al servicio para registrar el usuario
            redirectAttrs.addFlashAttribute("mensaje", "¡Cuenta creada exitosamente!");
            return "redirect:/login"; // Redirige al formulario de inicio de sesión
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "redirect:/registro"; // Redirige al formulario de registro en caso de error
        }
    }
}

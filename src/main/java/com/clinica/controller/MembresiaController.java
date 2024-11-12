package com.clinica.controller;

import com.clinica.services.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membresias")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    // Ruta para listar las membresías en la vista del cliente
    @GetMapping("/cliente")
    public String listarMembresiasCliente(Model model) {
        model.addAttribute("membresias", membresiaService.listarMembresias());
        return "membresiasCliente/listado"; // Renderiza el archivo `listado.html` en `membresiasCliente`
    }

    // (Opcional) Ruta para la vista de administración de membresías, si fuera necesario
    @GetMapping("/admin")
    public String listarMembresiasAdmin(Model model) {
        model.addAttribute("membresias", membresiaService.listarMembresias());
        return "membresiasAdmin/listado"; // Aquí podrías tener un listado de administración
    }
}

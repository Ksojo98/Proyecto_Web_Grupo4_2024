package com.clinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromocionesController {

    @GetMapping("/promociones/cliente")
    public String promocionesCliente() {
        return "promocionesCliente/listado"; // Asegúrate de que el nombre coincide con listado.html
    }
}

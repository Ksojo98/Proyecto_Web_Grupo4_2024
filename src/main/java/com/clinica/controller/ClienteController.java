package com.clinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @GetMapping("/cliente")
    public String clienteHome() {
        return "index_cliente"; // Vista específica para clientes que usa plantilla_cliente.html
    }
}

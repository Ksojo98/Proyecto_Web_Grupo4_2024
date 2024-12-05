package com.clinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @GetMapping("/cliente/clienteHome")
    public String clienteHome() {
        return "cliente/clienteHome";
    }
}

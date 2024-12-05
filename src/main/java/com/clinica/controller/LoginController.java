package com.clinica.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login/login"; 
    }
    
    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/adminHome";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CLIENTE"))) {
            return "redirect:/cliente/clienteHome";
        } else{
            return "redirect:/";
        }
    }
}
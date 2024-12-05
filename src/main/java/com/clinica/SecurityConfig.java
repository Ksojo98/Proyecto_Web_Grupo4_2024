package com.clinica.config;

import com.clinica.services.impl.DetalleUsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DetalleUsuarioServiceImpl detalleUsuarioService;

    public SecurityConfig(DetalleUsuarioServiceImpl detalleUsuarioService) {
        this.detalleUsuarioService = detalleUsuarioService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) 
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login","/login/**", "/registro/**","/index").permitAll() // Acceso sin restricciones
                .requestMatchers(
                        "/membresias/admin",
                        "/productos/admin",
                        "/promociones/admin", 
                        "/horarios/admin", 
                        "/fisioterapia/admin",
                        "/admin/adminHome").hasRole("ADMIN") // Solo administradores
                        
                .requestMatchers(
                        "/horarios/usuarios",
                        "/promociones/cliente",
                        "/productos/cliente",
                        "/membresias/cliente",
                        "/cliente/clienteHome").hasRole("CLIENTE") // Solo clientes
                        
                .anyRequest().authenticated() 

                )
                .formLogin(form -> form
                .loginPage("/login") 
                .defaultSuccessUrl("/default", true)
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout") 
                .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(detalleUsuarioService).passwordEncoder(passwordEncoder());
        return builder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}

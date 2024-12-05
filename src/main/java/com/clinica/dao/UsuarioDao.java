package com.clinica.dao;

import com.clinica.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    boolean existsByCorreo(String correo); // Verificar si un correo ya está registrado

    Optional<Usuario> findByCorreo(String correo); // Buscar un usuario por correo
}
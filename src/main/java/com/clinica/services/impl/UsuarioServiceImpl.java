package com.clinica.services.impl;

import com.clinica.dao.UsuarioDao;
import com.clinica.domain.Usuario;
import com.clinica.services.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;

    // Constructor para inyectar dependencias
    public UsuarioServiceImpl(UsuarioDao usuarioDao, PasswordEncoder passwordEncoder) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registrar(Usuario usuario) {
        // Verificar si el correo ya está registrado
        if (usuarioDao.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está en uso.");
        }

        // Hash de la contraseña
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));

        // Guardar usuario en la base de datos
        usuarioDao.save(usuario);
    }
}


package com.clinica.services.impl;

import com.clinica.dao.UsuarioDao;
import com.clinica.domain.Usuario;
import com.clinica.services.CorreoService;
import com.clinica.services.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;
    private final CorreoService correoService;

    public UsuarioServiceImpl(UsuarioDao usuarioDao, PasswordEncoder passwordEncoder, CorreoService correoService) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = passwordEncoder;
        this.correoService = correoService;
    }

    @Override
    public void registrar(Usuario usuario) {
        if (usuarioDao.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está en uso.");
        }

        String token = UUID.randomUUID().toString();
        usuario.setActivationToken(token);
        usuario.setActivo(false);
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        usuarioDao.save(usuario);

        correoService.enviarCorreoActivacion(usuario.getCorreo(), token);
    }

    @Override
    public boolean activarCuenta(String token) {
        Optional<Usuario> optionalUsuario = usuarioDao.findByActivationToken(token);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setActivo(true);
            usuario.setActivationToken(null);
            usuarioDao.save(usuario);
            return true;
        }

        return false;
    }

    @Override
    public boolean enviarEnlaceRecuperacion(String correo) {
        Optional<Usuario> optionalUsuario = usuarioDao.findByCorreo(correo);

        if (optionalUsuario.isEmpty()) {
            return false;
        }

        Usuario usuario = optionalUsuario.get();
        String token = UUID.randomUUID().toString();
        usuario.setTokenRecuperacion(token);
        usuarioDao.save(usuario);

        String enlace = "http://localhost:80/cambiar-contrasena?token=" + token;
        correoService.enviarCorreoRecuperacion(correo, enlace);
        return true;
    }

    @Override
    public boolean restablecerContrasena(String token, String nuevaContrasena) {
        Optional<Usuario> optionalUsuario = usuarioDao.findByTokenRecuperacion(token);

        if (optionalUsuario.isEmpty()) {
            return false;
        }

        Usuario usuario = optionalUsuario.get();
        usuario.setContraseña(passwordEncoder.encode(nuevaContrasena));
        usuario.setTokenRecuperacion(null);
        usuarioDao.save(usuario);
        return true;
    }

    @Override
    public boolean verificarTokenRecuperacion(String token) {
        return usuarioDao.findByTokenRecuperacion(token).isPresent();
    }
}

package com.clinica.services.impl;

import com.clinica.dao.UsuarioDao;
import com.clinica.domain.Usuario;
import com.clinica.services.DetalleUsuarioService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import org.springframework.security.authentication.DisabledException;

@Service
public class DetalleUsuarioServiceImpl implements DetalleUsuarioService {

    private final UsuarioDao usuarioDao;

    public DetalleUsuarioServiceImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Busca el usuario por correo
        Usuario usuario = usuarioDao.findByCorreo(correo)
                
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));

        // Verifica si la cuenta está activa
        if (!usuario.isActivo()) {
            throw new DisabledException("La cuenta no está activada."); // Lanza excepción personalizada
        }

        // Configura el usuario con su rol
        return new User(
                usuario.getCorreo(),
                usuario.getContraseña(), // Contraseña cifrada en la BD
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol())) // Rol personalizado
        );
    }
}
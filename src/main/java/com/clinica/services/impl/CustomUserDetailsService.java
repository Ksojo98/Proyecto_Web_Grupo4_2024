/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.services.impl;

import com.clinica.dao.UsuarioDao;
import com.clinica.domain.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioDao usuarioDao;

    public CustomUserDetailsService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioDao.findByCorreo(correo);

        if (optionalUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
        }

        Usuario usuario = optionalUsuario.get();

        // No agregues "ROLE_"; Spring Security lo hace automáticamente con hasRole()
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(usuario.getRol()));

        return new User(usuario.getCorreo(), usuario.getContraseña(), authorities);
    }

}

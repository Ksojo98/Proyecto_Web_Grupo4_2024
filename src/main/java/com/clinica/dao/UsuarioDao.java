package com.clinica.dao;

import com.clinica.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    /**
     * Verifica si ya existe un usuario con el correo especificado.
     *
     * @param correo Correo a verificar.
     * @return true si el correo ya está en uso, false en caso contrario.
     */
    boolean existsByCorreo(String correo);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo Correo electrónico del usuario.
     * @return Optional con el usuario encontrado, o vacío si no existe.
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Busca un usuario por su token de activación.
     *
     * @param activationToken Token de activación.
     * @return Optional con el usuario encontrado, o vacío si no existe.
     */
    Optional<Usuario> findByActivationToken(String activationToken);

    /**
     * Busca un usuario por su token de recuperación de contraseña.
     *
     * @param tokenRecuperacion Token de recuperación de contraseña.
     * @return Optional con el usuario encontrado, o vacío si no existe.
     */
    Optional<Usuario> findByTokenRecuperacion(String tokenRecuperacion);

 
}

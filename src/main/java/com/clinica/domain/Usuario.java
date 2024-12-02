package com.clinica.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "usuarios") // Mapeo a la tabla de la base de datos
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Column(name = "contraseña", nullable = false) // La columna en la base de datos seguirá siendo "contraseña"
    private String contraseña; // Spring Security requiere este nombre para el campo de la contraseña

    @Column(nullable = false)
    private String rol = "CLIENTE"; // Valor predeterminado
}

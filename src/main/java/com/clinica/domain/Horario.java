/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.domain;

/**
 *
 * @author Moke
 */
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Moke
 */

@Data
@Entity
@Table(name="horarios")
public class Horario implements Serializable {
    
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario;
    private String descripcion;
    private boolean activo;
    
    
}

/*CREATE TABLE horarios (
  id_horario INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  activo BOOLEAN,
  PRIMARY KEY (id_horario)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
*/
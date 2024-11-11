
package com.clinica.domain;


import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Data
@Entity
@Table(name="tarifas")
public class Tarifas implements Serializable{
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario;
    private String dia;
    private String hora;
    private boolean activo;
}

/*
CREATE TABLE horarios (
  id_horario INT NOT NULL AUTO_INCREMENT,
  dia date NOT NULL,
  hora varchar(30) not null,
  activo BOOLEAN,
  PRIMARY KEY (id_horario)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
*/
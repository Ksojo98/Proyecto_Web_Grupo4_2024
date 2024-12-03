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



@Data
@Entity
@Table(name="horariosFisioterapia")
public class Fisioterapia implements Serializable {
    
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario;
    private String dia;
    private String hora;
    private boolean activo;
}

/*
DROP TABLE IF EXISTS horarios_fisioterapia;
CREATE TABLE horarios_fisioterapia (
  id_horario int NOT NULL AUTO_INCREMENT,
  dia varchar(255) NOT NULL,
  hora varchar(255) NOT NULL,
  activo tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (id_horario)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para horarios
INSERT INTO horarios_fisioterapia (id_horario, dia, hora, activo) VALUES
(1,'Jueves','10:30',1),
(3,'Miercoles','16:00',1),
(4,'Viernes','15:00',1),
(6,'Miercoles','2:00',1),
(7,'Martes','7:00',1),
(8,'Viernes','8:00',1),
(9,'Miercoles','12:00',1),
(10,'Miercoles','14:00',1),
(11,'Sabado','18:00',1);

SELECT * FROM horarios_fisioterapia;
*/

/* Se elimina el esquema si existe y se elimina el usuario si ya está creado */
DROP SCHEMA IF EXISTS BOHDIWELLNESS;
DROP USER IF EXISTS 'usuario_prueba'@'%';

/* Se crea el esquema BOHDIWELLNESS */
CREATE SCHEMA BOHDIWELLNESS;

/* Se crea un usuario para la base de datos llamado "usuario_prueba" con la contraseña "Usuar1o_Clave." */
CREATE USER 'usuario_prueba'@'%' IDENTIFIED BY 'Usuar1o_Clave.';

/* Se asignan todos los privilegios sobre la base de datos BOHDIWELLNESS al usuario creado */
GRANT ALL PRIVILEGES ON BOHDIWELLNESS.* TO 'usuario_prueba'@'%';
FLUSH PRIVILEGES;

/* Se selecciona la base de datos BOHDIWELLNESS */
USE BOHDIWELLNESS;

CREATE TABLE horarios (
  id_horario INT NOT NULL AUTO_INCREMENT,
  dia VARCHAR(255) NOT NULL,
  hora VARCHAR(255) NOT NULL,
  activo TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id_horario)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4;


INSERT INTO horarios (dia, hora, activo) VALUES
('Jueves', '10:30', 1),
('Martes', '6:00', 1),
('Miercoles', '16:00', 1),
('Viernes', '15:00', 1),
('Sabado', '17:00', 0),
('Miercoles', '2:00', 1),
('Martes', '7:00', 1),
('Viernes', '8:00', 1),
('Miercoles', '12:00', 1);


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

/* La tabla horarios contiene horarios de actividades */
CREATE TABLE horarios (
  id_horario INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  activo BOOLEAN,
  PRIMARY KEY (id_horario)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


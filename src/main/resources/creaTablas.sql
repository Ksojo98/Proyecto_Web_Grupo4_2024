
/* Se elimina el esquema si existe y se elimina el usuario si ya está creado  */
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

-- Configuraciones iniciales
SET NAMES utf8mb4;
SET TIME_ZONE='+00:00';
SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

-- Tabla horarios GYM
DROP TABLE IF EXISTS horarios;
CREATE TABLE horarios (
  id_horario int NOT NULL AUTO_INCREMENT,
  dia varchar(255) NOT NULL,
  hora varchar(255) NOT NULL,
  activo tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (id_horario)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para horarios
INSERT INTO horarios (id_horario, dia, hora, activo) VALUES
(1,'Jueves','10:30',1),
(3,'Miercoles','16:00',1),
(4,'Viernes','15:00',1),
(5,'Sabado','17:00',0),
(6,'Miercoles','2:00',1),
(7,'Martes','7:00',1),
(8,'Viernes','8:00',1),
(9,'Miercoles','12:00',1),
(10,'Miercoles','14:00',1),
(11,'Sabado','18:00',1);

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


-- Tabla membresias
DROP TABLE IF EXISTS membresias;
CREATE TABLE membresias (
  id_membresia int NOT NULL AUTO_INCREMENT,
  nombre varchar(100) NOT NULL,
  descripcion text,
  precio decimal(10,2) NOT NULL,
  evaluacion_inicial tinyint(1) NOT NULL DEFAULT '0',
  fisioterapia_semanal tinyint(1) NOT NULL DEFAULT '0',
  sala_ejercicios tinyint(1) NOT NULL DEFAULT '0',
  terapia_manual tinyint(1) NOT NULL DEFAULT '0',
  equipos_especializados tinyint(1) NOT NULL DEFAULT '0',
  consultas_nutricion tinyint(1) NOT NULL DEFAULT '0',
  masajes_terapeuticos tinyint(1) NOT NULL DEFAULT '0',
  fisioterapia_domicilio tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (id_membresia)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para membresias
INSERT INTO membresias (id_membresia, nombre, descripcion, precio, evaluacion_inicial, fisioterapia_semanal, sala_ejercicios, terapia_manual, equipos_especializados, consultas_nutricion, masajes_terapeuticos, fisioterapia_domicilio) VALUES
(1,'Plan Básico','El plan básico ofrece una introducción a nuestros servicios.',100.00,1,1,1,0,0,0,0,0),
(2,'Plan Premium','El plan premium incluye sesiones de fisioterapia y más.',200.00,1,1,1,1,1,0,1,0),
(3,'Plan Diamante','Nuestro plan más completo con todos los beneficios.',300.00,1,1,1,1,1,1,1,1);

-- Tabla promociones
DROP TABLE IF EXISTS promociones;
CREATE TABLE promociones (
  id_promocion int NOT NULL AUTO_INCREMENT,
  titulo varchar(100) NOT NULL,
  descripcion text NOT NULL,
  precio decimal(10,2) NOT NULL,
  imagen varchar(255) NOT NULL,
  PRIMARY KEY (id_promocion)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para promociones
INSERT INTO promociones (id_promocion, titulo, descripcion, precio, imagen) VALUES
(1,'¡Promoción de Halloween!','Gel de calor/frío para alivio del dolor y recuperación muscular',102830.00,'https://firebasestorage.googleapis.com/v0/b/techshop-fbf53.appspot.com/o/techshop%2Fpromociones%2Fcalabaza.png?alt=media&token=bc22021b-6d7e-4187-86a3-77d43e07ba81'),
(2,'¡Promoción de Viernes negruzco!','Descarga muscular: Dos sesiones para superior e inferior',50000.00,'https://firebasestorage.googleapis.com/v0/b/techshop-fbf53.appspot.com/o/promociones%2F2_logo_clinica.png?alt=media'),
(3,'¡Promoción de Navidad!','Descarga muscular: Dos sesiones para superior e inferior',50000.00,'https://firebasestorage.googleapis.com/v0/b/techshop-fbf53.appspot.com/o/promociones%2Fnull_arbol_navideño.png?alt=media'),
(4,'Promoción Explosiva','Explosión Chida',10000.00,'https://firebasestorage.googleapis.com/v0/b/techshop-fbf53.appspot.com/o/promociones%2Fnull_logo_clinica.png?alt=media'),
(5,'Promoción Super Explosiva','Explosión Chida',10000.00,'https://firebasestorage.googleapis.com/v0/b/techshop-fbf53.appspot.com/o/promociones%2Fnull_logo_clinica.png?alt=media');

-- Restaurar configuraciones
SET SQL_MODE=DEFAULT;
SET FOREIGN_KEY_CHECKS=1;
SET UNIQUE_CHECKS=1;
SET SQL_NOTES=1;


CREATE TABLE usuarios (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  correo VARCHAR(100) NOT NULL UNIQUE,
  contraseña VARCHAR(255) NOT NULL,
  rol ENUM('ROLE_CLIENTE', 'ROLE_ADMIN') NOT NULL DEFAULT 'ROLE_CLIENTE',
  activo TINYINT(1) DEFAULT 0,
  activation_token VARCHAR(255) DEFAULT NULL,
  token_recuperacion VARCHAR(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO usuarios (correo, contraseña, rol) 
VALUES 
('admin@bodhiwellness.com', '$2a$12$21sdEU6RL/bbcMkQsHyWAelaOJW6gqIj5fRjwiVQKmRkWgVg5y9pO', 'ROLE_ADMIN'),  -- Contraseña: Admin123
('andgr1987@hotmail.com', '$2a$10$/aBOBwBmhrc4pLqAXBGsY.Vd6XicF0ttX9zUK9CPQuVllvz8nNls.', 'ROLE_CLIENTE');  -- Contraseña: Mariobros1987
UPDATE usuarios SET activo = 1 WHERE id_usuario = 1;
UPDATE usuarios SET activo = 1 WHERE id_usuario = 2;

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    imagen VARCHAR(2083), -- URL larga para la imagen
    stock INT NOT NULL -- Cantidad disponible
);

INSERT INTO productos (nombre, descripcion, precio, imagen, stock)
VALUES 
('Producto 1', 'Descripción del producto 1', 10000.00, 'https://via.placeholder.com/150', 20),
('Producto 2', 'Descripción del producto 2', 20000.00, 'https://via.placeholder.com/150', 15),
('Producto 3', 'Descripción del producto 3', 30000.00, 'https://via.placeholder.com/150', 10),
('Producto 4', 'Descripción del producto 4', 5000.00, 'https://via.placeholder.com/150', 25),
('Producto 5', 'Descripción del producto 5', 20000.00, 'https://via.placeholder.com/150', 7),
('Producto 6', 'Descripción del producto 6', 15000.00, 'https://via.placeholder.com/150', 0);


CREATE TABLE factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  fecha date,  
  total double,
  estado int,
  PRIMARY KEY (id_factura),
  foreign key fk_factura_usuario (id_usuario) references usuarios(id_usuario)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  id_producto INT NOT NULL,
  precio double, 
  cantidad int,
  PRIMARY KEY (id_venta),
  foreign key fk_ventas_factura (id_factura) references factura(id_factura),
  foreign key fk_ventas_producto (id_producto) references productos(id_producto)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


INSERT INTO factura (id_factura, id_usuario, fecha, total, estado) VALUES
(1,1,'2024-06-05',211560,2),
(2,2,'2024-06-07',554340,2),
(4,1,'2024-08-15',244140,1),
(5,2,'2024-09-17',414800,1),
(3, 1, '2024-08-01', 500000, 1);

INSERT INTO venta (id_venta, id_factura, id_producto, precio, cantidad) VALUES
(1, 1, 5, 45000, 3),
(2, 1, 3, 15780, 2),
(3, 1, 6, 15000, 3),
(4, 2, 5, 45000, 1),
(5, 2, 6, 154000, 3),
(6, 2, 3, 15780, 3),
(7, 4, 6, 154000, 1),
(8, 4, 1, 57000, 1), 
(9, 4, 2, 330000, 2),
(10, 1, 1, 57000, 2),
(11, 1, 2, 27600, 3),
(12, 1, 3, 15780, 3);


CREATE TABLE constante (
    id_constante INT AUTO_INCREMENT NOT NULL,
    atributo VARCHAR(25) NOT NULL,
    valor VARCHAR(150) NOT NULL,
	PRIMARY KEY (id_constante))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO constante (atributo,valor) VALUES 

('dominio','localhost'),
('certificado','c:/cert'),
('dolar','520.75'),
('paypal.client-id','AUjOjw5Q1I0QLTYjbvRS0j4Amd8xrUU2yL9UYyb3TOTcrazzd3G3lYRc6o7g9rOyZkfWEj2wxxDi0aRz'),
('paypal.client-secret','EMdb08VRlo8Vusd_f4aAHRdTE14ujnV9mCYPovSmXCquLjzWd_EbTrRrNdYrF1-C4D4o-57wvua3YD2u'),
('paypal.mode','sandbox'),
('urlPaypalCancel','http://localhost/payment/cancel'),
('urlPaypalSuccess','http://localhost/payment/success');

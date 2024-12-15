package com.clinica.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer idProducto;
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private Integer stock;
}

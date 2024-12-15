package com.clinica.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto {
    private int cantidad; //Almacenar la cantidad de items de un producto

    public Item() {
    }

    public Item(Producto producto) {
        super.setIdProducto(producto.getIdProducto());
        super.setDescripcion(producto.getDescripcion());
        super.setPrecio(producto.getPrecio());
        super.setNombre(producto.getNombre());
        super.setStock(producto.getStock());
        super.setImagen(producto.getImagen());
        this.cantidad = 0;
    }
}


/*

@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    private String nombre; listo
    private String descripcion; listo
    private Double precio; listo
    private String imagen; listo
    private Integer stock; listo

*/
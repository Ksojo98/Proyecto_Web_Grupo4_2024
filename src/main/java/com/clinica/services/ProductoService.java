package com.clinica.services;

import com.clinica.domain.Producto;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ProductoService {
    List<Producto> listarProductos(); // Listar todos los productos
    Producto obtenerProductoPorId(Integer id); // Obtener un producto por ID
    void guardarProducto(Producto producto, MultipartFile file) throws IOException; // Guardar o actualizar un producto
    void eliminarProducto(Integer id); // Eliminar un producto
}

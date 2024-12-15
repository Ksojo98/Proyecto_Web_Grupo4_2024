package com.clinica.services.impl;

import com.clinica.dao.ProductoDao;
import com.clinica.domain.Producto;
import com.clinica.services.ProductoService;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }

    public Producto obtenerProductoPorId(long id) {
        return productoDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    public void guardarProducto(Producto producto, MultipartFile file) throws IOException {
        // Si hay un archivo, subimos la imagen a Firebase y obtenemos el enlace
        if (file != null && !file.isEmpty()) {
            String imageUrl = subirImagenFirebase(file, producto.getIdProducto());
            producto.setImagen(imageUrl);
        }

        // Guardamos el producto en la base de datos
        productoDao.save(producto);
    }

    public void eliminarProducto(long id) {
        Optional<Producto> producto = productoDao.findById(id);
        producto.ifPresent(p -> {
            eliminarImagenFirebase(p.getIdProducto()); // Eliminamos la imagen de Firebase
            productoDao.deleteById(id); // Eliminamos el producto de la BD
        });
    }

    private String subirImagenFirebase(MultipartFile file, Long productoId) throws IOException {
        String fileName = "productos/" + productoId + "_" + file.getOriginalFilename();
        StorageClient.getInstance().bucket().create(fileName, file.getBytes(), file.getContentType());
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                StorageClient.getInstance().bucket().getName(),
                fileName.replace("/", "%2F"));
    }

    private void eliminarImagenFirebase(Long productoId) {
        String fileName = "productos/" + productoId;
        Blob blob = StorageClient.getInstance().bucket().get(fileName);
        if (blob != null && blob.exists()) {
            blob.delete();
        }
    }

    @Override
    public Producto obtenerProductoPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarProducto(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

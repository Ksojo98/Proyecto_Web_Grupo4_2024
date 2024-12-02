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

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Integer id) {
        return productoDao.findById(id).orElse(null);
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

    @Override
    public void eliminarProducto(Integer id) {
        Optional<Producto> producto = productoDao.findById(id);
        producto.ifPresent(p -> {
            eliminarImagenFirebase(p.getIdProducto()); // Eliminamos la imagen de Firebase
            productoDao.deleteById(id); // Eliminamos el producto de la BD
        });
    }

    private String subirImagenFirebase(MultipartFile file, Integer productoId) throws IOException {
        String fileName = "productos/" + productoId + "_" + file.getOriginalFilename();
        StorageClient.getInstance().bucket().create(fileName, file.getBytes(), file.getContentType());
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                StorageClient.getInstance().bucket().getName(),
                fileName.replace("/", "%2F"));
    }

    private void eliminarImagenFirebase(Integer productoId) {
        String fileName = "productos/" + productoId;
        Blob blob = StorageClient.getInstance().bucket().get(fileName);
        if (blob != null && blob.exists()) {
            blob.delete();
        }
    }
}

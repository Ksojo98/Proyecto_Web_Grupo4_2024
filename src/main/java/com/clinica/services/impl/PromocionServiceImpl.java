package com.clinica.services.impl;

import com.clinica.dao.PromocionDao;
import com.clinica.domain.Promocion;
import com.clinica.services.PromocionService;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PromocionServiceImpl implements PromocionService {

    @Autowired
    private PromocionDao promocionDao;

    @Override
    public List<Promocion> listarPromociones() {
        return promocionDao.findAll();
    }

    @Override
    public Promocion obtenerPromocionPorId(Long id) {
        return promocionDao.findById(id).orElse(null);
    }

    @Override
    public void guardarPromocion(Promocion promocion, MultipartFile file) throws IOException {
        // Si hay un archivo, subimos la imagen a Firebase y obtenemos el enlace
        if (file != null && !file.isEmpty()) {
            String imageUrl = subirImagenFirebase(file, promocion.getId_promocion());
            promocion.setImagen(imageUrl);
        }

        // Si la promoción ya tiene un ID, actualizamos la promoción existente
        if (promocion.getId_promocion() != null) {
            // Verificamos si la promoción ya existe en la base de datos
            Optional<Promocion> promocionExistente = promocionDao.findById(promocion.getId_promocion());
            if (promocionExistente.isPresent()) {
                Promocion pExistente = promocionExistente.get();
                pExistente.setTitulo(promocion.getTitulo());
                pExistente.setDescripcion(promocion.getDescripcion());
                pExistente.setPrecio(promocion.getPrecio());
                // Actualizamos la imagen solo si se ha subido un nuevo archivo
                if (promocion.getImagen() != null) {
                    pExistente.setImagen(promocion.getImagen());
                }
                promocionDao.save(pExistente);
                return; // Termina la ejecución, ya que se actualizó la promoción existente
            }
        }

        // Si no tiene ID o no se encuentra en la BD, guarda la promoción como nueva
        promocionDao.save(promocion);
    }

    @Override
    public void eliminarPromocion(Long id) {
        Optional<Promocion> promocion = promocionDao.findById(id);
        promocion.ifPresent(p -> {
            // Eliminamos la imagen de Firebase antes de eliminar la promoción de la BD
            eliminarImagenFirebase(p.getId_promocion());
            promocionDao.deleteById(id);
        });
    }

    private String subirImagenFirebase(MultipartFile file, Long promocionId) throws IOException {
        // Construimos el nombre de archivo para Firebase usando el ID de la promoción y el nombre original del archivo
        String fileName = "promociones/" + promocionId + "_" + file.getOriginalFilename();
        
        // Subimos el archivo a Firebase Storage
        StorageClient.getInstance().bucket().create(fileName, file.getBytes(), file.getContentType());
        
        // Construimos la URL pública para acceder a la imagen
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                StorageClient.getInstance().bucket().getName(),
                fileName.replace("/", "%2F"));
    }

    private void eliminarImagenFirebase(Long promocionId) {
        // Construimos el nombre del archivo en Firebase usando el ID de la promoción
        String fileName = "promociones/" + promocionId;
        // Obtenemos el blob (archivo) de Firebase
        Blob blob = StorageClient.getInstance().bucket().get(fileName);

        // Verificamos que el blob exista antes de intentar eliminarlo
        if (blob != null && blob.exists()) {
            blob.delete();
        }
    }
}

package com.clinica.services;

import com.clinica.domain.Promocion;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface PromocionService {
    List<Promocion> listarPromociones();
    Promocion obtenerPromocionPorId(Long id);
    void guardarPromocion(Promocion promocion, MultipartFile file) throws IOException;
    void eliminarPromocion(Long id);
}

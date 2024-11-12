package com.clinica.services;

import com.clinica.domain.Membresia;
import java.util.List;

public interface MembresiaService {

    // Método para listar todas las membresías
    List<Membresia> listarMembresias();

    // Método para obtener una membresía por su ID
    Membresia obtenerMembresiaPorId(Long id);

    // Método para guardar (agregar o actualizar) una membresía
    void guardarMembresia(Membresia membresia);

    // Método para eliminar una lista de membresías por sus IDs
    void eliminarMembresias(List<Long> ids);
}

package com.clinica.services;

import com.clinica.domain.Membresia;
import java.util.List;

public interface MembresiaService {

    List<Membresia> listarMembresias();

    Membresia obtenerMembresiaPorId(Long id);

    void guardarMembresia(Membresia membresia);

    // Elimina una membresía por su ID
    void eliminarMembresiaPorId(Long id);
}

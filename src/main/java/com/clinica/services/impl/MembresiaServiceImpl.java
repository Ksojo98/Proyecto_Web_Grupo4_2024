package com.clinica.services.impl;

import com.clinica.dao.MembresiaDao;
import com.clinica.domain.Membresia;
import com.clinica.services.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaServiceImpl implements MembresiaService {

    @Autowired
    private MembresiaDao membresiaDao;

    @Override
    public List<Membresia> listarMembresias() {
        return membresiaDao.findAll();
    }

    @Override
    public Membresia obtenerMembresiaPorId(Long id) {
        Optional<Membresia> membresia = membresiaDao.findById(id);
        return membresia.orElse(null); // Retorna null si no encuentra la membresía
    }

    @Override
    @Transactional
    public void guardarMembresia(Membresia membresia) {
        membresiaDao.save(membresia); // Guarda o actualiza la membresía
    }

    @Override
    @Transactional
    public void eliminarMembresias(List<Long> ids) {
        for (Long id : ids) {
            membresiaDao.deleteById(id); // Elimina cada membresía por su ID
        }
    }
}

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
        return membresia.orElse(null);
    }

    @Override
    @Transactional
    public void guardarMembresia(Membresia membresia) {
        membresiaDao.save(membresia);
    }

    @Override
    @Transactional
    public void eliminarMembresiaPorId(Long id) {
        membresiaDao.deleteById(id);
    }
}

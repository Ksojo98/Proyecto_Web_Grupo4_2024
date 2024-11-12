package com.clinica.services.impl;

import com.clinica.dao.MembresiaDao;
import com.clinica.domain.Membresia;
import com.clinica.services.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembresiaServiceImpl implements MembresiaService {

    @Autowired
    private MembresiaDao membresiaDao;

    @Override
    public List<Membresia> listarMembresias() {
        return membresiaDao.findAll();
    }
}

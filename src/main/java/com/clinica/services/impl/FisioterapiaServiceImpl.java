/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.services.impl;

import com.clinica.dao.FisioterapiaDao;
import com.clinica.domain.Fisioterapia;
import com.clinica.services.FisioterapiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moke
 */
@Service
public class FisioterapiaServiceImpl implements FisioterapiaService {

    @Autowired
    private FisioterapiaDao fisioterapiaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Fisioterapia> getHorarios(boolean activos) {
        var lista = fisioterapiaDao.findAll();

        if (activos) {

            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Fisioterapia gethorario(Fisioterapia fisioterapia) {
        return fisioterapiaDao.findById(fisioterapia.getId_horario()).orElse(null);

    }

    @Override
    @Transactional
    public void save(Fisioterapia fisioterapia) {
        fisioterapiaDao.save(fisioterapia);
    }

    @Override
    @Transactional
    public void delete(Fisioterapia fisioterapia) {
        fisioterapiaDao.delete(fisioterapia);
    }

    @Override
    @Transactional(readOnly = true)
    public Fisioterapia getHorario(Fisioterapia fisioterapia) {
        return fisioterapiaDao.findById(fisioterapia.getId_horario()).orElse(null);
    }

}

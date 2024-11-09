/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.services.impl;

/**
 *
 * @author Moke
 */
import com.clinica.dao.HorarioDao;
import com.clinica.domain.Horario;
import com.clinica.services.HorarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioDao horarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Horario> getHorarios(boolean activos) {
        var lista = horarioDao.findAll();

        if (activos) {
            //se deben eliminar los inactivos
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Horario gethorario(Horario horario) {
        return horarioDao.findById(horario.getId_horario()).orElse(null);
      
    }

    @Override
    @Transactional
    public void save(Horario horario) {
        horarioDao.save(horario);
    }

    @Override
    @Transactional
    public void delete(Horario horario) {
        horarioDao.delete(horario);
    }

    @Override
    public Horario getProducto(Horario horario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

  

}

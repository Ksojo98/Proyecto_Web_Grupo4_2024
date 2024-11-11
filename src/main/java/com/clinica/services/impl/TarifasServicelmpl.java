
package com.clinica.services.impl;



import com.clinica.dao.TarifasDao;
import com.clinica.domain.Tarifas;
import com.clinica.services.TarifasServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class TarifasServicelmpl implements TarifasServices {
      
    @Autowired
    private TarifasDao tarifasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Tarifas> getTarifas(boolean activos) {
        var lista = tarifasDao.findAll();

        if (activos) {
            //se deben eliminar los inactivos
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Tarifas gettarifas(Tarifas tarifas) {
        return tarifasDao.findById(tarifas.getId_tarifas()).orElse(null);
      
    }

    @Override
    @Transactional
    public void save(Tarifas tarifas) {
        tarifasDao.save(tarifas);
    }

    @Override
    @Transactional
    public void delete(Tarifas tarifas) {
        tarifasDao.delete(tarifas);
    }

    @Override
    public Tarifas getTarifas(Tarifas tarifas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     @Override
    @Transactional
    public void save(Tarifas tarifas) {
        tarifasDao.save(tarifas);
    }

    @Override
    @Transactional
    public void delete(Tarifas tarifas) {
        tarifasDao.delete(tarifas);
    
    }  
}

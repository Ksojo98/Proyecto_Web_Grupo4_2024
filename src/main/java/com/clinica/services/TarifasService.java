package com.clinica.services;

import com.clinica.dao.TarifasDao;
import com.clinica.domain.Tarifas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarifasService {

    @Autowired
    private TarifasDao tarifasRepository;

   
    public Tarifas getTarifaById(Long id) {
        Optional<Tarifas> tarifa = tarifasRepository.findById(id);
        return tarifa.orElse(null);  // Devuelve la tarifa si existe, o null si no
    }

    public void save(Tarifas tarifa) {
        tarifasRepository.save(tarifa);
    }
}

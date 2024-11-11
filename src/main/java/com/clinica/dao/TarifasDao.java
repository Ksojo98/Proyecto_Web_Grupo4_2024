package com.clinica.dao;

import com.clinica.domain.Tarifas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifasDao extends JpaRepository<Tarifas, Long> {
    // No es necesario agregar métodos extra si estás usando JpaRepository
}
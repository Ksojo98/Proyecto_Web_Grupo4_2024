
package com.clinica.dao;

import com.clinica.domain.Tarifas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface TarifasDao extends JpaRepository<Tarifas,Long> {
    
}

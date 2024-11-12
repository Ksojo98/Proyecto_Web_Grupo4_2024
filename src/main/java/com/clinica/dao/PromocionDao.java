package com.clinica.dao;

import com.clinica.domain.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocionDao extends JpaRepository<Promocion, Long> {
}

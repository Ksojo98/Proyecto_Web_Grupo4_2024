package com.clinica.dao;

import com.clinica.domain.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembresiaDao extends JpaRepository<Membresia, Long> {
}

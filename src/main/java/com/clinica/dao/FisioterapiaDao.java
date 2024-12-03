/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.dao;

import com.clinica.domain.Fisioterapia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Moke
 */
public interface FisioterapiaDao extends JpaRepository<Fisioterapia,Long> {
    
}

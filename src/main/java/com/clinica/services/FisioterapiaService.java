/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.services;

import com.clinica.domain.Fisioterapia;
import java.util.List;

/**
 *
 * @author Moke
 */
public interface FisioterapiaService {
    
        //Se obtiene un arraylist con los registros de la tabla producto
    //Todos los registros o solo activos

    public List<Fisioterapia> getHorarios(boolean activos);


    public Fisioterapia getHorario(Fisioterapia fisioterapia);


    public void save(Fisioterapia fisioterapia);

    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Fisioterapia fisioterapia);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.services;
import com.clinica.domain.Tarifas;
import java.util.List;
/**
 *
 * @author josem
 */
public interface TarifasServices {
        //Se obtiene un arraylist con los registros de la tabla producto
    //Todos los registros o solo activos
    
    public List<Tarifas> getTarifas(boolean activos);
    // Se obtiene un Producto, a partir del id de un producto
    public Tarifas getTarifas(Tarifas tarifas);
    
    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Tarifas tarifas);
    
    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Tarifas horario);
}

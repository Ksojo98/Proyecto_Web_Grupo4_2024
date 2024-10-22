package com.tienda.services;

import com.tienda.domain.Categoria;
import java.util.List;

/**
 *
 * @author Andgr
 */
public interface CategoriaService {
    //Se obtiene un arraylist con los registros
    //de la tabla categoria
    //Todos los registros...o sólo los activos
    public List<Categoria> getCategorias(boolean activos);
}

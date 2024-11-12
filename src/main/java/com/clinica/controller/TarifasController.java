
package com.clinica.controller;


import com.clinica.domain.Tarifas;
import com.clinica.services.TarifasService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tarifas")
public class TarifasController {

    @Autowired
    private TarifasService tarifasService;

    // Modificar tarifa
    @GetMapping("/modificar/{id_tarifa}")
    public String tarifaModificar(@PathVariable("id_tarifa") Long idTarifa, Model model) {
        // Obtiene la tarifa a modificar por su ID
        Tarifas tarifa = tarifasService.getTarifaById(idTarifa);
        
        // Pasa la tarifa a la vista para que el usuario la pueda modificar
        model.addAttribute("tarifa", tarifa);
        
        return "tarifasAdmin/modifica"; // Vista donde se modificará la tarifa
    }

    // Guardar la tarifa modificada
    @PostMapping("/guardar")
    public String guardar(Tarifas tarifa) {
        // Guarda la tarifa (se asume que la tarifa ya está modificada)
        tarifasService.save(tarifa);
        
        // Redirige al listado de tarifas después de guardar
        return "redirect:/tarifas/admin";  // O la ruta donde deseas redirigir al guardar
    }
}



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

   
    @GetMapping("/modificar/{id_tarifa}")
    public String tarifaModificar(@PathVariable("id_tarifa") Long idTarifa, Model model) {
       
        Tarifas tarifa = tarifasService.getTarifaById(idTarifa);
        model.addAttribute("tarifa", tarifa);
        return "tarifasAdmin/modifica"; 
    }

    // Guardar la tarifa modificada
    @PostMapping("/guardar")
    public String guardar(Tarifas tarifa) {
      
        tarifasService.save(tarifa);

        return "redirect:/tarifas/admin";  
    }
}


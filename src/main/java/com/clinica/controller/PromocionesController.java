package com.clinica.controller;

import com.clinica.domain.Promocion;
import com.clinica.services.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequestMapping("/promociones")
public class PromocionesController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping("/cliente")
    public String promocionesCliente(Model model) {
        model.addAttribute("promociones", promocionService.listarPromociones());
        return "promocionesCliente/listado";
    }

    @GetMapping("/admin")
    public String promocionesAdmin(Model model) {
        model.addAttribute("promociones", promocionService.listarPromociones());
        return "promocionesAdmin/listado";
    }

    @GetMapping("/admin/nueva")
    public String nuevaPromocion(Model model) {
        model.addAttribute("promocion", new Promocion());
        return "promocionesAdmin/modificar";
    }

    @PostMapping("/admin/guardar")
    public String guardarPromocion(@ModelAttribute Promocion promocion, 
                                   @RequestParam("file") MultipartFile file) throws IOException {
        // Guardar o actualizar la promoción
        promocionService.guardarPromocion(promocion, file);
        return "redirect:/promociones/admin";
    }

    @GetMapping("/admin/editar/{id}")
    public String editarPromocion(@PathVariable("id") Long idPromocion, Model model) {
        Promocion promocion = promocionService.obtenerPromocionPorId(idPromocion);
        model.addAttribute("promocion", promocion);
        return "promocionesAdmin/modificar";
    }

    @GetMapping("/admin/eliminar/{id}")
    public String eliminarPromocion(@PathVariable("id") Long idPromocion) {
        promocionService.eliminarPromocion(idPromocion);
        return "redirect:/promociones/admin";
    }
}

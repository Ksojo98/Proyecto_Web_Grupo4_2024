/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.controller;

import com.clinica.domain.Fisioterapia;
import com.clinica.services.FisioterapiaService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/fisioterapia")
public class FisioterapiaController {

    @Autowired
    private final FisioterapiaService fisioterapiaService;

    public FisioterapiaController(FisioterapiaService fisioterapiaService) {
        this.fisioterapiaService = fisioterapiaService;
    }

    @GetMapping("/fisioterapia/admin")
    public String listarHorariosFisioterapia(Model model) {
        List<Fisioterapia> horarios = fisioterapiaService.getHorarios(true);

        // Ordenar horarios por día y hora
        horarios = horarios.stream()
                .sorted(Comparator.comparing(Fisioterapia::getDia).thenComparing(Fisioterapia::getHora))
                .collect(Collectors.toList());

        List<String> diasSemana = List.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");

        model.addAttribute("horarios", horarios);
        model.addAttribute("diasSemana", diasSemana);
        return "fisioterapia/listado";
    }

    @GetMapping("/admin")
    public String mostrarHorariosUsuariosFisioterapia(Model model) {
        List<String> diasSemana = List.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
        List<Fisioterapia> horarios = fisioterapiaService.getHorarios(true);
        model.addAttribute("diasSemana", diasSemana);
        model.addAttribute("horarios", horarios);
        return "fisioterapiaAdmin/listado";
    }

    @PostMapping("/guardar")
    public String guardarFisioterapia(Fisioterapia fisioterapia) {
        fisioterapiaService.save(fisioterapia);
        return "redirect:/fisioterapia/admin";
    }

    @GetMapping("/eliminar/{id_horario}")
    public String eliminarFisioterapia(@PathVariable("id_horario") Long idHorario) {
        Fisioterapia fisioterapia = new Fisioterapia();
        fisioterapia.setId_horario(idHorario);
        fisioterapiaService.delete(fisioterapia);
        return "redirect:/fisioterapia/admin";
    }

    @GetMapping("/modificar/{id_horario}")
    public String horarioModificarFisioterapia(@PathVariable("id_horario") Long idHorario, Model model) {
        Fisioterapia fisioterapia = new Fisioterapia();
        fisioterapia.setId_horario(idHorario);
        fisioterapia = fisioterapiaService.getHorario(fisioterapia);
        model.addAttribute("fisioterapia", fisioterapia);
        return "fisioterapiaAdmin/modifica";
    }
}

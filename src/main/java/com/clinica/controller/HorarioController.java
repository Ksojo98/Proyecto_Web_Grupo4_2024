/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.controller;

import com.clinica.domain.Horario;
import com.clinica.services.HorarioService;
import java.util.ArrayList;
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
@RequestMapping("/horarios")  // PRUEBA 24
/*prueba*/
public class HorarioController {

    @Autowired
    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping("/horarios/admin")
    public String listarHorarios(Model model) {
        List<Horario> horarios = horarioService.getHorarios(true);

        // Ordenar horarios por día y hora
        horarios = horarios.stream()
                .sorted(Comparator.comparing(Horario::getDia).thenComparing(Horario::getHora))
                .collect(Collectors.toList());

        List<String> diasSemana = List.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");

        model.addAttribute("horarios", horarios);
        model.addAttribute("diasSemana", diasSemana);
        return "horarioAdmin/listado";
    }

    @GetMapping("/admin")
    public String mostrarHorariosUsuarios(Model model) {
        List<String> diasSemana = List.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
        List<Horario> horarios = horarioService.getHorarios(true);
        model.addAttribute("diasSemana", diasSemana);
        model.addAttribute("horarios", horarios);
        return "horarioAdmin/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Horario horario) {
        horarioService.save(horario);
        return "redirect:/horarios/admin";
    }

    @GetMapping("/eliminar/{id_horario}")
    public String eliminar(@PathVariable("id_horario") Long idHorario) {
        Horario horario = new Horario();
        horario.setId_horario(idHorario);
        horarioService.delete(horario);
        return "redirect:/horarios/admin";
    }

    // Modificar horario
    @GetMapping("/modificar/{id_horario}")
    public String horarioModificar(@PathVariable("id_horario") Long idHorario, Model model) {
        Horario horario = new Horario();
        horario.setId_horario(idHorario);
        horario = horarioService.getHorario(horario);
        model.addAttribute("horario", horario);
        return "horarioAdmin/modifica"; // Ajuste a la subcarpeta correcta
    }
}

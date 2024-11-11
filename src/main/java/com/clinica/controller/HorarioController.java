/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.controller;

import com.clinica.domain.Horario;
import com.clinica.services.HorarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/horarios")  // PRUEBA
public class HorarioController {

    @GetMapping("/admin")
    public String mostrarHorariosAdmin(Model model) {
        var listaHorarios = horarioService.getHorarios(false);
        model.addAttribute("horarios", listaHorarios);
        return "horarioAdmin/listado";
    }

    @Autowired
    private HorarioService horarioService;

    // Listado para usuarios o admin
  @GetMapping("/listado")
public String Listado(Model model) {
    var lista = horarioService.getHorarios(false);
    model.addAttribute("horarios", lista);
    model.addAttribute("diasSemana", List.of("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
    model.addAttribute("totalHorarios", lista.size());
    return "horarioAdmin/listado";
}


    @PostMapping("/guardar")
    public String guardar(Horario horario) {
        horarioService.save(horario);
        return "redirect:/horarios/listado"; // Redirige a la vista correcta después de guardar
    }

    @GetMapping("/eliminar/{id_horario}")
    public String eliminar(@PathVariable("id_horario") Long idHorario) {
        Horario horario = new Horario();
        horario.setId_horario(idHorario);
        horarioService.delete(horario);
        return "redirect:/horarios/listado";
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

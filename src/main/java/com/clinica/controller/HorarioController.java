/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.controller;

import com.clinica.domain.Horario;
import com.clinica.services.HorarioService;
import com.clinica.services.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/horarios")  // Cambio aquí: Rutas más semánticas para horarios
public class HorarioController {
    @GetMapping("/admin")
public String mostrarHorariosAdmin(Model model) {
    var listaHorarios = horarioService.getHorarios(false);
    model.addAttribute("horarios", listaHorarios);
    return "horarioAdmin/listado";
}


    @Autowired
    private HorarioService horarioService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;


    // Listado para usuarios o admin
    @GetMapping("/listado")
    public String Listado(Model model) {
        var lista = horarioService.getHorarios(false);
        model.addAttribute("horarios", lista);
        model.addAttribute("totalHorarios", lista.size());
        return "horarioAdmin/listado"; // Ajuste de retorno
    }

    // Guardar horario con imagen
    @PostMapping("/guardar")
    public String horarioGuardar(Horario horario,
                                 @RequestParam("imagenFile") MultipartFile imagenFile) {        
       
        horarioService.save(horario);
        return "redirect:/horarios/admin"; // Redirigir a la lista de horarios admin
    }

    // Eliminar horario
    @GetMapping("/eliminar/{idHorario}")
    public String horarioEliminar(@PathVariable("idHorario") Long idHorario) {
        Horario horario = new Horario();
        horario.setId_horario(idHorario);
        horarioService.delete(horario);
        return "redirect:/horarios/admin";
    }

    // Modificar horario
    @GetMapping("/modificar/{idHorario}")
    public String horarioModificar(@PathVariable("idHorario") Long idHorario, Model model) {
        Horario horario = new Horario();
        horario.setId_horario(idHorario);
        horario = horarioService.getHorario(horario);
        model.addAttribute("horario", horario);
        return "horarioAdmin/modifica"; // Ajuste a la subcarpeta correcta
    }
}

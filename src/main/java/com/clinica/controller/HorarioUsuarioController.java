package com.clinica.controller;

import com.clinica.domain.Horario;
import com.clinica.services.HorarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/horarios")
public class HorarioUsuarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping("/usuarios")
    public String listarHorariosUsuarios(Model model) {
        List<Horario> horarios = horarioService.getHorarios(true);
        List<String> diasSemana = List.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
        
        model.addAttribute("horarios", horarios);
        model.addAttribute("diasSemana", diasSemana);
        model.addAttribute("horarios", horarios != null ? horarios : new ArrayList<>());
        
        return "horarioUsuarios/listado"; // Asegúrate de que la carpeta y archivo existan en templates
    }
}

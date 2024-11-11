/*package com.clinica.controller;

import com.clinica.domain.Tarifas;
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
@RequestMapping("/Tarifas")  // PRUEBA
public class TarifasController {

    @GetMapping("/admin")
    public String mostrarHorariosAdmin(Model model) {
        var listaHorarios = horarioService.getTarifas(false);
        model.addAttribute("tarifas", listaTarifas);
        return "TarifasAdmin/listado";
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
    public String guardar(Tarifas tarifas) {
        horarioService.save(tarifas);
        return "redirect:/horarios/listado"; // Redirige a la vista correcta después de guardar
    }

    @GetMapping("/eliminar/{id_horario}")
    public String eliminar(@PathVariable("id_horario") Long idTarifas) {
        Tarifas tarifas = new Tarifas();
        tarifas.setId_tarifas(idTarifas);
        horarioService.delete(tarifas);
        return "redirect:/horarios/listado";
    }

    // Modificar horario
    @GetMapping("/modificar/{id_horario}")
    public String horarioModificar(@PathVariable("id_horario") Long idHorario, Model model) {
        Tarifas tarifas = new Tarifas();
        tarifas.setId_tarifas(tarifas);
        tarifas = horarioService.getHorario(tarifas);
        model.addAttribute("tarifas", tarifas);
        return "horarioAdmin/modifica"; // Ajuste a la subcarpeta correcta
    }
}
*/
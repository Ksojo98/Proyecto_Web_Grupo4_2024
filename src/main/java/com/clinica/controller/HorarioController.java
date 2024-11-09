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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class HorarioController {
    
    
    @Autowired
    private HorarioService horarioService;
    
    @GetMapping("/listado")
    public String Listado(Model model){
        var lista=horarioService.getHorarios(false);
        
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());
    return "/producto/listado";
    }
    
    //aca estoy codeando
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String horarioGuardar(Horario horario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
       
        horarioService.save(horario);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String horarioEliminar(Horario horario) {
        horarioService.delete(horario);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String horarioModificar(Horario horario, Model model) {
        horario = horarioService.getProducto(horario);
        model.addAttribute("producto", horario);
        return "/producto/modifica";
    }
}
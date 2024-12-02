package com.clinica.controller;

import com.clinica.domain.Producto;
import com.clinica.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/admin")
    public String productosAdmin(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productosAdmin/listado";
    }

    @GetMapping("/admin/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productosAdmin/modificar";
    }

    @PostMapping("/admin/guardar")
    public String guardarProducto(@ModelAttribute Producto producto,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        productoService.guardarProducto(producto, file);
        return "redirect:/productos/admin";
    }

    @GetMapping("/admin/editar/{id}")
    public String editarProducto(@PathVariable("id") Integer idProducto, Model model) {
        Producto producto = productoService.obtenerProductoPorId(idProducto);
        model.addAttribute("producto", producto);
        return "productosAdmin/modificar";
    }

    @GetMapping("/admin/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer idProducto) {
        productoService.eliminarProducto(idProducto);
        return "redirect:/productos/admin";
    }
    
    // Vista para CLIENTE
    @GetMapping("/cliente")
    public String productosCliente(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productosCliente/listado"; // Devuelve la vista de productos para clientes
    }
}

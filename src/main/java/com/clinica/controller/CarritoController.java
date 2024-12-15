/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.controller;

import com.clinica.dao.ProductoDao;
import com.clinica.domain.Item;
import com.clinica.domain.Producto;
import com.clinica.services.ItemService;
import com.clinica.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Moke
 */
@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoDao productoDao;

    //se agrega un producto al carrito de compras
@GetMapping("/carrito/agregar/{idProducto}")
public ModelAndView agregar(Model model, @PathVariable("idProducto") Long idProducto) {
    // Obtener el producto desde la base de datos
    var producto = productoDao.findById(idProducto).orElse(null);

    if (producto == null) {
        model.addAttribute("error", "Producto no encontrado.");
        return new ModelAndView("/carrito/error");
    }

    if (producto.getStock() <= 0) {
        model.addAttribute("error", "El producto no tiene stock disponible.");
        return new ModelAndView("/carrito/error");
    }

    // Crear el item y agregarlo al carrito
    Item item = new Item(producto);
    itemService.save(item);

    // Actualizar la lista de items en el carrito
    var listaItems = itemService.gets(); // Obtener todos los items en el carrito
    var totalItems = listaItems.size(); // Total de items en el carrito
    var totalVenta = itemService.getTotal(); // Total de la venta

    model.addAttribute("listaItems", listaItems);
    model.addAttribute("listaTotal", totalItems);
    model.addAttribute("carritoTotal", totalVenta);

    // Retornar la vista actualizada del carrito
    return new ModelAndView("/carrito/fragmentos :: verCarrito");
}


    @GetMapping("/carrito/listado")
    public String listado(Model model) {
        var lista = itemService.gets();
        var total = lista.size();
        model.addAttribute("items", lista);
        model.addAttribute("listaTotal", total);
        return ("/carrito/listado");

    }

    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminar(Model model, Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificar(Model model, Item item) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }

    @PostMapping("/carrito/guardar")
    public String guardar(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }

 @GetMapping("/facturar/carrito")
public String facturar() {
    itemService.facturar();
    return "redirect:/productos/cliente";
}


}

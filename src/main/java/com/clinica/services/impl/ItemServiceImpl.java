package com.clinica.services.impl;

import com.clinica.dao.FacturaDao;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.clinica.dao.ProductoDao;
import com.clinica.dao.UsuarioDao;
import com.clinica.dao.VentaDao;
import com.clinica.domain.Factura;
import com.clinica.domain.Item;
import com.clinica.domain.Usuario;
import com.clinica.domain.Venta;
import com.clinica.services.ItemService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpSession session;

    @Override
    public List<Item> gets() {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems == null) {
            listaItems = new ArrayList<>(); // Si no existe, inicializa una nueva lista
            session.setAttribute("listaItems", listaItems);
        }
        return listaItems;
    }

    @Override
    public Item get(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (Item i : listaItems) {
                if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            var posicion = -1;
            var existe = false;
            for (var i : listaItems) {
                posicion++;
                if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                listaItems.remove(posicion);
                session.setAttribute("listaItems", listaItems);
            }
        }
    }

    @Override
    public void save(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }

        var existe = false;
        for (var i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                existe = true;
                if (i.getCantidad() < i.getStock()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
        session.setAttribute("listaItems", listaItems);
        System.out.println("Carrito actualizado: " + listaItems);

    }

    @Override
    public void update(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (var i : listaItems) {
                if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                    i.setCantidad(item.getCantidad());
                    session.setAttribute("listaItems", listaItems);
                    break;
                }
            }
        }
    }

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;

    @Override
    public void facturar() {
        // Se debe recuperar el correo del usuario autenticado
        String correo = "";
        var principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            correo = userDetails.getUsername(); // Supongamos que el correo está en el principal como username
        } else {
            if (principal != null) {
                correo = principal.toString();
            }
        }

        if (correo.isBlank()) {
            System.out.println("Correo en blanco...");
            return;
        }

        // Usar Optional para buscar el usuario por correo
        Optional<Usuario> optionalUsuario = usuarioDao.findByCorreo(correo);

        if (optionalUsuario.isEmpty()) {
            System.out.println("Usuario no existe en usuarios...");
            return;
        }

        Usuario usuario = optionalUsuario.get();

        // Se debe registrar la factura incluyendo el usuario
        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

        // Se debe registrar las ventas de cada producto -actualizando existencias-
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            double total = 0;
            for (Item i : listaItems) {
                var producto = productoDao.getReferenceById(i.getIdProducto());
                if (producto.getStock() >= i.getCantidad()) {
                    Venta venta = new Venta(factura.getIdFactura(),
                            i.getIdProducto(),
                            i.getPrecio(),
                            i.getCantidad());
                    ventaDao.save(venta);
                    producto.setStock(producto.getStock() - i.getCantidad());
                    productoDao.save(producto);
                    total += i.getCantidad() * i.getPrecio();
                }
            }

            // Se debe registrar el total de la venta en la factura
            factura.setTotal(total);
            facturaDao.save(factura);

            // Se debe limpiar el carrito la lista...
            listaItems.clear();
        }
    }

    //@Override
    public double getTotal() {
        //Se debe registrar las ventas de cada producto -actualizando existencias-
        @SuppressWarnings("unchecked")
        var listaItems = (List<Item>) session.getAttribute("listaItems");
        double total = 0;
        if (listaItems != null) {
            for (Item i : listaItems) {
                total += i.getCantidad() * i.getPrecio();
            }
        }
        return total;
    }
}

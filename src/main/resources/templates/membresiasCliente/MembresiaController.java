package templates.membresiasCliente;


import com.clinica.domain.Membresia;
import com.clinica.services.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membresias")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    // Ruta para listar las membresías en la vista del cliente
    @GetMapping("/cliente")
    public String listarMembresiasCliente(Model model) {
        model.addAttribute("membresias", membresiaService.listarMembresias());
        return "membresiasCliente/listadoCliente"; // Redirige al archivo `listadoCliente.html` en `membresiasCliente`
    }

    // Ruta para listar las membresías en la vista de administración
    @GetMapping("/admin")
    public String listarMembresiasAdmin(Model model) {
        model.addAttribute("membresias", membresiaService.listarMembresias());
        return "membresiasAdmin/listado"; // Renderiza el archivo `listado.html` en `membresiasAdmin`
    }

    // Ruta para mostrar el formulario de edición de membresía
    @GetMapping("/admin/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Membresia membresia = membresiaService.obtenerMembresiaPorId(id);
        model.addAttribute("membresia", membresia);
        return "membresiasAdmin/editar"; // Renderiza el archivo `editar.html` en `membresiasAdmin`
    }

    // Ruta para guardar los cambios de edición de membresía
    @PostMapping("/admin/editar")
    public String guardarEdicionMembresia(@ModelAttribute Membresia membresia) {
        membresiaService.guardarMembresia(membresia);
        return "redirect:/membresias/admin"; // Redirecciona a la vista de administración de membresías
    }

    // Ruta para eliminar una membresía
    @PostMapping("/admin/eliminar/{id}")
    public String eliminarMembresia(@PathVariable("id") Long id) {
        membresiaService.eliminarMembresiaPorId(id);
        return "redirect:/membresias/admin"; // Redirige a la vista de administración después de eliminar
    }

    // Ruta para mostrar el formulario de agregar nueva membresía
    @GetMapping("/admin/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("membresia", new Membresia());
        return "membresiasAdmin/agregar"; // Renderiza el archivo `agregar.html` en `membresiasAdmin`
    }

    // Ruta para agregar una nueva membresía
    @PostMapping("/admin/agregar")
    public String agregarMembresia(@ModelAttribute Membresia membresia) {
        membresiaService.guardarMembresia(membresia);
        return "redirect:/membresias/admin"; // Redirecciona a la vista de administración de membresías
    }
}

package com.sise.ahorroapp.backend.controller;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sise.ahorroapp.backend.entidad.Usuario;
import com.sise.ahorroapp.backend.servicio.UsuarioServicio;

@Controller
public class WebUsuarioController {
	
	

	@PostMapping("/usuarios/actualizar")
	public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario) {
	    usuarioServicio.guardarUsuario(usuario);
	    return "redirect:/usuarios?actualizado=true";
	}

	@GetMapping("/usuarios/nuevo")
	public String mostrarFormularioRegistro(Model model) {
	    model.addAttribute("usuario", new Usuario());
	    return "nuevo_usuario";	    
	 }   
	
	   @Autowired
	    private UsuarioServicio usuarioServicio; // usa tu interfaz directamente

	    @GetMapping("/usuarios")
	    public String mostrarUsuarios(Model model) {
	        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
	        model.addAttribute("usuarios", usuarios);
	        return "usuarios"; // Thymeleaf buscará usuarios.html en /resources/templates
	    }
    
	    @PostMapping("/usuarios/guardar")
	    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
	        usuarioServicio.guardarUsuario(usuario);
	        return "redirect:/index?creado=true"; // ✅ aquí entra mensaje_exito.js
	    }



	    
	    @GetMapping("/usuarios/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
	        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id)
	            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
	        model.addAttribute("usuario", usuario);
	        return "nuevo_usuario"; // usamos el mismo formulario
	    }
	    
	    /*@GetMapping("/usuarios/eliminar/{id}")
	    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	        Optional<Usuario> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);

	        if (usuarioOpt.isPresent()) {
	            Usuario usuario = usuarioOpt.get();

	            if (usuario.getMovimientos() != null && !usuario.getMovimientos().isEmpty()) {
	                redirectAttributes.addFlashAttribute("mensajeError", "No se puede eliminar. El usuario tiene movimientos registrados.");
	                return "redirect:/usuarios?error=usuario-con-movimientos";
	            }

	            usuarioServicio.eliminarUsuario(id);
	            redirectAttributes.addFlashAttribute("mensajeExito", "Usuario eliminado correctamente");
	        }

	        return "redirect:/usuarios";
	    }*/
	    
	    @GetMapping("/usuarios/desactivar/{id}")
	    public String desactivarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	        Optional<Usuario> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);
	        if (usuarioOpt.isPresent()) {
	            Usuario usuario = usuarioOpt.get();
	            usuario.setActivo(false); // lo desactiva
	            usuarioServicio.guardarUsuario(usuario);
	            redirectAttributes.addFlashAttribute("mensajeExito", "Usuario desactivado correctamente.");
	        }
	        return "redirect:/usuarios";
	    }
	    
	    @GetMapping("/usuarios/activar/{id}")
	    public String activarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	        Optional<Usuario> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);
	        if (usuarioOpt.isPresent()) {
	            Usuario usuario = usuarioOpt.get();
	            usuario.setActivo(true);
	            usuarioServicio.guardarUsuario(usuario);
	            redirectAttributes.addFlashAttribute("mensajeExito", "Usuario activado correctamente.");
	        }
	        return "redirect:/usuarios";
	    }
   
}

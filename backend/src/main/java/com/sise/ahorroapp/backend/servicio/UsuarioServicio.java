package com.sise.ahorroapp.backend.servicio;

import com.sise.ahorroapp.backend.entidad.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioServicio {
    Usuario guardarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    void eliminarUsuario(Long id);
}
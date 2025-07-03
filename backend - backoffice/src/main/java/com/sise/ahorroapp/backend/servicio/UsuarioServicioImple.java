package com.sise.ahorroapp.backend.servicio;
import com.sise.ahorroapp.backend.entidad.Usuario;
import com.sise.ahorroapp.backend.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServicioImple implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }*/
    
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
            usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        }
        return usuarioRepository.save(usuario); // ✅ Retorna el usuario guardado
    }


    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id); // <-- aquí implementamos el método
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    @Override
    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> listarUsuariosActivos() {
        return usuarioRepository.findByActivoTrue();
    }


}


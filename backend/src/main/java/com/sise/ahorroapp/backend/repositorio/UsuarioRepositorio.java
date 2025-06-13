package com.sise.ahorroapp.backend.repositorio;

import com.sise.ahorroapp.backend.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados si lo necesitas más adelante
}

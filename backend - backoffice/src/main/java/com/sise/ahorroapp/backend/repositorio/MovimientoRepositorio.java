package com.sise.ahorroapp.backend.repositorio;

import com.sise.ahorroapp.backend.entidad.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepositorio extends JpaRepository<Movimiento, Long> {
}

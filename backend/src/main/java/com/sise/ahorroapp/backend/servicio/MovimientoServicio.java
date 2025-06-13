package com.sise.ahorroapp.backend.servicio;

import com.sise.ahorroapp.backend.entidad.Movimiento;
import java.util.List;
import java.util.Optional;

public interface MovimientoServicio {
    Movimiento guardarMovimiento(Movimiento movimiento);
    List<Movimiento> listarMovimientos();
    Optional<Movimiento> obtenerMovimientoPorId(Long id);  // Este
    void eliminarMovimiento(Long id);   
}

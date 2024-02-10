package com.devsu.cuenta.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devsu.cuenta.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaCuentaIdAndFechaBetween(Long cuentaId, Date fechaInicio, Date fechaFin);
}
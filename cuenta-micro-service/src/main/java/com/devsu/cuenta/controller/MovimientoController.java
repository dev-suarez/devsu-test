package com.devsu.cuenta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.service.MovimientoService;

/**
 * Controlador para la gestión de movimientos de cuentas.
 */
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    /**
     * Obtiene todos los movimientos registrados.
     * @return Lista de movimientos.
     */
    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        log.info("[getAllMovimientos] Obteniendo todos los movimientos.");
        return movimientoService.findAll();
    }

    /**
     * Crea un nuevo movimiento.
     * @param movimiento Datos del movimiento a crear.
     * @return ResponseEntity con el movimiento creado y el estado HTTP.
     */
    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) {
        log.info("[createMovimiento] Creando movimientos.");
        Movimiento nuevoMovimiento = movimientoService.saveMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMovimiento);
    }

    /**
     * Obtiene un movimiento específico por su identificador.
     * @param id Identificador del movimiento.
     * @return ResponseEntity con el movimiento encontrado o un estado de no encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        return movimientoService.findMovimientoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un movimiento existente.
     * @param id Identificador del movimiento a actualizar.
     * @param movimiento Datos actualizados del movimiento.
     * @return Movimiento actualizado.
     */
    @PutMapping("/{id}")
    public Movimiento updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        return movimientoService.updateMovimiento(id, movimiento);
    }

    /**
     * Elimina un movimiento existente.
     * @param id Identificador del movimiento a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
    }
}

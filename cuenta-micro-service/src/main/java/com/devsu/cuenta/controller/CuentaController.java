package com.devsu.cuenta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.service.CuentaService;

/**
 * Controlador para la gestión de cuentas.
 */
@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final Logger log = LoggerFactory.getLogger(CuentaController.class);

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    /**
     * Obtiene todas las cuentas existentes.
     * @return Lista de cuentas.
     */
    @GetMapping
    public List<Cuenta> getAllCuentas() {
        log.info("[getAllCuentas] Obteniendo todos las cuentas.");
        return cuentaService.findAll();
    }

    /**
     * Crea una nueva cuenta.
     * @param cuenta Datos de la cuenta a crear.
     * @return Cuenta creada.
     */
    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        log.info("[createCuenta] Creando una cuenta.");
        return cuentaService.saveCuenta(cuenta);
    }

    /**
     * Actualiza una cuenta existente.
     * @param id Identificador de la cuenta a actualizar.
     * @param cuenta Datos actualizados de la cuenta.
     * @return Cuenta actualizada.
     */
    @PutMapping("/{id}")
    public Cuenta updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return cuentaService.updateCuenta(id, cuenta);
    }

    /**
     * Elimina una cuenta existente.
     * @param id Identificador de la cuenta a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
    }

    /**
     * Obtiene una cuenta por su identificador.
     * @param id Identificador de la cuenta.
     * @return ResponseEntity con la cuenta encontrada o un estado de no encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        return cuentaService.findCuentaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
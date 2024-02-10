package com.devsu.cuenta.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta.dto.ReporteEstadoCuentaDTO;
import com.devsu.cuenta.service.ReporteService;

/**
 * Controlador para la generación de reportes de estado de cuenta.
 */
@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final Logger log = LoggerFactory.getLogger(ReporteController.class);

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    /**
     * Obtiene un reporte del estado de cuenta para un cliente específico, basado en un rango de fechas.
     * @param clienteId Identificador del cliente para el cual se generará el reporte.
     * @param fechaInicio Fecha de inicio del rango para el reporte.
     * @param fechaFin Fecha de fin del rango para el reporte.
     * @return ResponseEntity con el reporte generado o un mensaje de error en caso de fallo.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReporteEstadoCuenta(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        log.info("[getReporteEstadoCuenta] Iniciando: Cliente ID: {}", clienteId);

        if (clienteId == null || fechaInicio == null || fechaFin == null) {
            return ResponseEntity.badRequest()
                    .body("Los parámetros clienteId, fechaInicio y fechaFin son obligatorios.");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            return ResponseEntity.badRequest().body("La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        try {
            ReporteEstadoCuentaDTO reporte = reporteService.generarReporteEstadoCuenta(clienteId, fechaInicio,
                    fechaFin);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al generar el reporte: " + e.getMessage());
        }
    }
}

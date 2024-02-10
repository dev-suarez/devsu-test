package com.devsu.cuenta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.devsu.cuenta.dto.ReporteEstadoCuentaDTO;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.repository.MovimientoRepository;

/**
 * Servicio para la generación de reportes de estado de cuenta.
 */
@Service
@Transactional
public class ReporteService {
    private final Logger log = LoggerFactory.getLogger(ReporteService.class);

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final WebClient webClient;

    public ReporteService(
            CuentaRepository cuentaRepository,
            MovimientoRepository movimientoRepository,
            WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    /**
     * Genera un reporte del estado de cuenta para un cliente específico, basado en un rango de fechas.
     * @param clienteId Identificador del cliente para el cual se generará el reporte.
     * @param fechaInicio Fecha de inicio del rango para el reporte.
     * @param fechaFin Fecha de fin del rango para el reporte.
     * @return ReporteEstadoCuentaDTO con la información del reporte generado.
     */
    public ReporteEstadoCuentaDTO generarReporteEstadoCuenta(
            Long clienteId,
            LocalDate fechaInicio,
            LocalDate fechaFin) {
        log.info("[generarReporteEstadoCuenta] Inicio.");
        String nombreCliente = obtenerNombreCliente(clienteId);
        List<Cuenta> cuentasCliente = cuentaRepository.findByClienteId(clienteId);
        List<ReporteEstadoCuentaDTO.DetalleCuenta> detallesCuentas = new ArrayList<>();

        for (Cuenta cuenta : cuentasCliente) {
            Date inicio = java.sql.Date.valueOf(fechaInicio);
            Date fin = java.sql.Date.valueOf(fechaFin);

            List<Movimiento> movimientosCuenta = movimientoRepository.findByCuentaCuentaIdAndFechaBetween(
                    cuenta.getCuentaId(), inicio, fin);

            List<ReporteEstadoCuentaDTO.DetalleMovimiento> detallesMovimientos = movimientosCuenta.stream()
                    .map(this::convertirADetalleMovimiento)
                    .collect(Collectors.toList());

            ReporteEstadoCuentaDTO.DetalleCuenta detalleCuenta = new ReporteEstadoCuentaDTO.DetalleCuenta(
                    cuenta.getNumeroCuenta(),
                    cuenta.getTipoCuenta(),
                    cuenta.getSaldoInicial(),
                    detallesMovimientos);

            detallesCuentas.add(detalleCuenta);
        }

        return new ReporteEstadoCuentaDTO(clienteId, nombreCliente, detallesCuentas);
    }

    /**
     * Obtiene el nombre del cliente a partir de su identificador.
     * @param clienteId Identificador del cliente.
     * @return Nombre del cliente.
     */
    private String obtenerNombreCliente(Long clienteId) {
        return webClient.get()
                .uri("/clientes/{id}/nombre", clienteId)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Considerar manejar esto de manera no bloqueante
    }

    /**
     * Convierte un movimiento a su representación detallada para incluir en el reporte.
     * @param movimiento Movimiento a convertir.
     * @return Detalle del movimiento para el reporte.
     */
    private ReporteEstadoCuentaDTO.DetalleMovimiento convertirADetalleMovimiento(Movimiento movimiento) {
        return new ReporteEstadoCuentaDTO.DetalleMovimiento(
                movimiento.getFecha(),
                movimiento.getTipoMovimiento(),
                movimiento.getValor(),
                movimiento.getSaldo());
    }
}

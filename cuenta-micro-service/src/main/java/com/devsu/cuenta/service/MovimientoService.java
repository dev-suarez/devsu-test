package com.devsu.cuenta.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.cuenta.exception.SaldoInsuficienteException;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.repository.MovimientoRepository;

/**
 * Servicio para la gestión de movimientos de cuentas.
 */
@Service
@Transactional
public class MovimientoService {
	private final MovimientoRepository movimientoRepository;
	private final CuentaRepository cuentaRepository;

	public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
		this.movimientoRepository = movimientoRepository;
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Encuentra y devuelve todos los movimientos registrados.
	 * @return Lista de todos los movimientos.
	 */
	public List<Movimiento> findAll() {
		return movimientoRepository.findAll();
	}

	/**
	 * Guarda un nuevo movimiento y actualiza el saldo de la cuenta relacionada.
	 * @param movimiento Movimiento a guardar.
	 * @return Movimiento guardado con saldo actualizado.
	 * @throws RuntimeException si la cuenta asociada no se encuentra.
	 * @throws SaldoInsuficienteException si el saldo resultante es negativo.
	 */
	public Movimiento saveMovimiento(Movimiento movimiento) {
		Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta())
				.orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

		BigDecimal saldoActualizado = cuenta.getSaldoInicial().add(movimiento.getValor());

		if (saldoActualizado.compareTo(BigDecimal.ZERO) < 0) {
			throw new SaldoInsuficienteException("Saldo no disponible");
		}

		// cuenta.setSaldoInicial(saldoActualizado);
		cuentaRepository.save(cuenta);
		movimiento.setSaldo(saldoActualizado);
		return movimientoRepository.save(movimiento);
	}

	/**
	 * Busca un movimiento por su identificador.
	 * @param id Identificador del movimiento a buscar.
	 * @return Movimiento encontrado o un Optional vacío si no se encuentra.
	 */
	public Optional<Movimiento> findMovimientoById(Long id) {
		return movimientoRepository.findById(id);
	}

	/**
	 * Actualiza un movimiento existente.
	 * @param id Identificador del movimiento a actualizar.
	 * @param movimiento Datos del movimiento a actualizar.
	 * @return Movimiento actualizado.
	 */
	public Movimiento updateMovimiento(Long id, Movimiento movimiento) {
		movimiento.setMovimientoId(id);
		return movimientoRepository.save(movimiento);
	}

	/**
	 * Elimina un movimiento por su identificador.
	 * @param id Identificador del movimiento a eliminar.
	 */
	public void deleteMovimiento(Long id) {
		movimientoRepository.deleteById(id);
	}

}

package com.devsu.cuenta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.repository.CuentaRepository;

/**
 * Servicio para la gestión de cuentas.
 */
@Service
@Transactional
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    /**
     * Encuentra y devuelve todas las cuentas registradas.
     * @return Lista de todas las cuentas.
     */
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    /**
     * Guarda una nueva cuenta en la base de datos.
     * @param cuenta Cuenta a guardar.
     * @return Cuenta guardada.
     */
    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    /**
     * Actualiza una cuenta existente.
     * @param id Identificador de la cuenta a actualizar.
     * @param cuenta Datos actualizados de la cuenta.
     * @return Cuenta actualizada.
     */
    public Cuenta updateCuenta(Long id, Cuenta cuenta) {
        cuenta.setNumeroCuenta(id);
        return cuentaRepository.save(cuenta);
    }

    /**
     * Elimina una cuenta por su identificador.
     * @param id Identificador de la cuenta a eliminar.
     */
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    /**
     * Busca una cuenta por su identificador.
     * @param id Identificador de la cuenta.
     * @return Cuenta encontrada o un Optional vacío si no se encuentra.
     */
    public Optional<Cuenta> findCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    /**
     * Encuentra todas las cuentas asociadas a un cliente específico.
     * @param clienteId Identificador del cliente.
     * @return Lista de cuentas asociadas al cliente.
     */
    public List<Cuenta> findCuentasByClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}
package com.devsu.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.repository.ClienteRepository;

/**
 * Servicio para la gestión de clientes.
 */
@Service
@Transactional
public class ClienteService {
	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	/**
	 * Obtiene todos los clientes registrados.
	 * 
	 * @return Lista de todos los clientes.
	 */
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	/**
	 * Guarda un nuevo cliente en la base de datos.
	 * 
	 * @param cliente Cliente a guardar.
	 * @return Cliente guardado.
	 */
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	/**
	 * Busca un cliente por su identificador.
	 * 
	 * @param id Identificador del cliente.
	 * @return Cliente encontrado o un Optional vacío si no se encuentra.
	 */
	public Optional<Cliente> findClienteById(Long id) {
		return clienteRepository.findById(id);
	}

	/**
	 * Actualiza un cliente existente.
	 * 
	 * @param id      Identificador del cliente a actualizar.
	 * @param cliente Datos actualizados del cliente.
	 * @return Cliente actualizado.
	 */
	public Cliente updateCliente(Long id, Cliente cliente) {
		cliente.setClienteId(id);
		return clienteRepository.save(cliente);
	}

	/**
	 * Elimina un cliente por su identificador.
	 * 
	 * @param id Identificador del cliente a eliminar.
	 */
	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}

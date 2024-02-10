package com.devsu.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {
	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Optional<Cliente> findClienteById(Long id) {
		return clienteRepository.findById(id);
	}

	public Cliente updateCliente(Long id, Cliente cliente) {
		cliente.setClienteId(id);
		return clienteRepository.save(cliente);
	}

	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}

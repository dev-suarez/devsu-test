package com.devsu.cliente.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.service.ClienteService;

/**
 * Controlador para la gestión de clientes.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Obtiene todos los clientes registrados.
     * 
     * @return Lista de todos los clientes.
     */
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    /**
     * Crea un nuevo cliente.
     * 
     * @param cliente Datos del cliente a crear.
     * @return Cliente creado.
     */
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    /**
     * Obtiene un cliente específico por su identificador.
     * 
     * @param id Identificador del cliente.
     * @return ResponseEntity con el cliente encontrado o un estado de no
     *         encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.findClienteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un cliente existente.
     * 
     * @param id      Identificador del cliente a actualizar.
     * @param cliente Datos actualizados del cliente.
     * @return Cliente actualizado.
     */
    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    /**
     * Elimina un cliente por su identificador.
     * 
     * @param id Identificador del cliente a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }

    /**
     * Obtiene el nombre de un cliente por su identificador.
     * 
     * @param id Identificador del cliente.
     * @return ResponseEntity con el nombre del cliente o un estado de no
     *         encontrado.
     */
    @GetMapping("/{id}/nombre")
    public ResponseEntity<String> getClienteNombreById(@PathVariable Long id) {
        return clienteService.findClienteById(id)
                .map(cliente -> ResponseEntity.ok(cliente.getNombre()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package com.devsu.cliente.service;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.repository.ClienteRepository;

@SpringBootTest
class ClienteServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Test
    void cuandoSeSolicitaTodosLosClientes_entoncesSeDevuelveLaLista() {
        Cliente cliente1 = new Cliente();
        cliente1.setClienteId(1L);
        cliente1.setContrasenha("contraseña1");
        cliente1.setEstado(true);
        cliente1.setNombre("Nombre1");
        cliente1.setGenero("Masculino");
        cliente1.setEdad(30);
        cliente1.setIdentificacion(12345678L);
        cliente1.setDireccion("Direccion1");
        cliente1.setTelefono("123456789");

        Cliente cliente2 = new Cliente();
        cliente2.setClienteId(2L);
        cliente2.setContrasenha("contraseña2");
        cliente2.setEstado(false);
        cliente2.setNombre("Nombre2");
        cliente2.setGenero("Femenino");
        cliente2.setEdad(25);
        cliente2.setIdentificacion(87654321L);
        cliente2.setDireccion("Direccion2");
        cliente2.setTelefono("987654321");

        List<Cliente> listaClientes = Arrays.asList(cliente1, cliente2);
        Mockito.when(clienteRepository.findAll()).thenReturn(listaClientes);

        List<Cliente> resultado = clienteService.findAll();

        assertEquals(2, resultado.size(), "Se esperaba una lista de 2 clientes");
        Mockito.verify(clienteRepository, Mockito.times(1)).findAll();
    }
}
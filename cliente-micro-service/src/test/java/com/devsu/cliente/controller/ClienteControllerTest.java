package com.devsu.cliente.controller;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.service.ClienteService;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void cuandoSeSolicitaTodosLosClientes_entoncesSeDevuelveLaLista() throws Exception {
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
        Mockito.when(clienteService.findAll()).thenReturn(listaClientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].clienteId", is(1)))
                .andExpect(jsonPath("$[1].clienteId", is(2)));
    }
}

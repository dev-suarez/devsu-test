package com.devsu.cuenta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devsu.cuenta.dto.ReporteEstadoCuentaDTO;
import com.devsu.cuenta.service.ReporteService;

public class ReporteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReporteService reporteService;

    @InjectMocks
    private ReporteController reporteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reporteController).build();
    }

    @Test
    public void getReporteEstadoCuentaTest() throws Exception {
        Long clienteId = 1L;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.now();

        when(reporteService.generarReporteEstadoCuenta(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(new ReporteEstadoCuentaDTO(clienteId, "Test Cliente", Collections.emptyList()));

        mockMvc.perform(get("/reportes")
                .param("clienteId", clienteId.toString())
                .param("fechaInicio", fechaInicio.toString())
                .param("fechaFin", fechaFin.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(clienteId));
    }
}
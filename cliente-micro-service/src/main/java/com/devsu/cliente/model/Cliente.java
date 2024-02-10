package com.devsu.cliente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends Persona {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    private String contrasenha;
    private Boolean estado;
    
	public Long getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	public String getContrasenha() {
		return contrasenha;
	}
	
	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
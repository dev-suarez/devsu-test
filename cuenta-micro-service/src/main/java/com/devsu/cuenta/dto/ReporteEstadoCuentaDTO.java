package com.devsu.cuenta.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReporteEstadoCuentaDTO {
	private Long clienteId;
	private String nombreCliente;
	private List<DetalleCuenta> cuentas;

	public ReporteEstadoCuentaDTO(Long clienteId, String nombreCliente, List<DetalleCuenta> cuentas) {
		this.clienteId = clienteId;
		this.nombreCliente = nombreCliente;
		this.cuentas = cuentas;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public List<DetalleCuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<DetalleCuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public static class DetalleCuenta {
		private Long numeroCuenta;
		private String tipoCuenta;
		private BigDecimal saldoInicial;
		private List<DetalleMovimiento> movimientos;

		public DetalleCuenta(Long numeroCuenta, String tipoCuenta, BigDecimal saldoInicial,
				List<DetalleMovimiento> movimientos) {
			super();
			this.numeroCuenta = numeroCuenta;
			this.tipoCuenta = tipoCuenta;
			this.saldoInicial = saldoInicial;
			this.movimientos = movimientos;
		}

		public Long getNumeroCuenta() {
			return numeroCuenta;
		}

		public void setNumeroCuenta(Long numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
		}

		public String getTipoCuenta() {
			return tipoCuenta;
		}

		public void setTipoCuenta(String tipoCuenta) {
			this.tipoCuenta = tipoCuenta;
		}

		public BigDecimal getSaldoInicial() {
			return saldoInicial;
		}

		public void setSaldoInicial(BigDecimal saldoInicial) {
			this.saldoInicial = saldoInicial;
		}

		public List<DetalleMovimiento> getMovimientos() {
			return movimientos;
		}

		public void setMovimientos(List<DetalleMovimiento> movimientos) {
			this.movimientos = movimientos;
		}

	}

	public static class DetalleMovimiento {
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private Date fecha;
		private String tipoMovimiento;
		private BigDecimal valor;
		private BigDecimal saldoPostMovimiento;

		public DetalleMovimiento(Date fecha, String tipoMovimiento, BigDecimal valor, BigDecimal saldoPostMovimiento) {
			super();
			this.fecha = fecha;
			this.tipoMovimiento = tipoMovimiento;
			this.valor = valor;
			this.saldoPostMovimiento = saldoPostMovimiento;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public String getTipoMovimiento() {
			return tipoMovimiento;
		}

		public void setTipoMovimiento(String tipoMovimiento) {
			this.tipoMovimiento = tipoMovimiento;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public BigDecimal getSaldoPostMovimiento() {
			return saldoPostMovimiento;
		}

		public void setSaldoPostMovimiento(BigDecimal saldoPostMovimiento) {
			this.saldoPostMovimiento = saldoPostMovimiento;
		}
	}
}
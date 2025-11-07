package co.edu.unbosque.tallerpatrones.dto;

import java.math.BigDecimal;

public class ExtraPedidoDTO {

	private String extraEnvioId;
	private String nombreExtra;
	private String tipoPrecio;
	private BigDecimal valorConfigurado;
	private BigDecimal costoAplicado;

	public ExtraPedidoDTO() {
	}
	
	

	public ExtraPedidoDTO(String extraEnvioId, String nombreExtra, String tipoPrecio, BigDecimal valorConfigurado,
			BigDecimal costoAplicado) {
		super();
		this.extraEnvioId = extraEnvioId;
		this.nombreExtra = nombreExtra;
		this.tipoPrecio = tipoPrecio;
		this.valorConfigurado = valorConfigurado;
		this.costoAplicado = costoAplicado;
	}



	public String getExtraEnvioId() {
		return extraEnvioId;
	}

	public void setExtraEnvioId(String extraEnvioId) {
		this.extraEnvioId = extraEnvioId;
	}

	public String getNombreExtra() {
		return nombreExtra;
	}

	public void setNombreExtra(String nombreExtra) {
		this.nombreExtra = nombreExtra;
	}

	public String getTipoPrecio() {
		return tipoPrecio;
	}

	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	public BigDecimal getValorConfigurado() {
		return valorConfigurado;
	}

	public void setValorConfigurado(BigDecimal valorConfigurado) {
		this.valorConfigurado = valorConfigurado;
	}

	public BigDecimal getCostoAplicado() {
		return costoAplicado;
	}

	public void setCostoAplicado(BigDecimal costoAplicado) {
		this.costoAplicado = costoAplicado;
	}
}

package co.edu.unbosque.tallerpatrones.dto;

import java.math.BigDecimal;

public class ExtraEnvioDTO {

	private String id;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String tipoPrecio;
	private BigDecimal valor;
	private boolean activo;

	public ExtraEnvioDTO() {
	}
	
	

	public ExtraEnvioDTO(String id, String codigo, String nombre, String descripcion, String tipo, String tipoPrecio,
			BigDecimal valor, boolean activo) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.tipoPrecio = tipoPrecio;
		this.valor = valor;
		this.activo = activo;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoPrecio() {
		return tipoPrecio;
	}

	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}

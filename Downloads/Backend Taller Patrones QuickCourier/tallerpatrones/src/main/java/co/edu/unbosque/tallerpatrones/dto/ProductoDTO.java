package co.edu.unbosque.tallerpatrones.dto;

import java.math.BigDecimal;

public class ProductoDTO {

	private String id;
	private String sku;
	private String nombre;
	private String descripcion;
	private String categoria;
	private BigDecimal precio;
	private Integer pesoGramos;
	private boolean activo;

	public ProductoDTO() {
	}
	
	

	public ProductoDTO(String id, String sku, String nombre, String descripcion, String categoria, BigDecimal precio,
			Integer pesoGramos, boolean activo) {
		super();
		this.id = id;
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precio = precio;
		this.pesoGramos = pesoGramos;
		this.activo = activo;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getPesoGramos() {
		return pesoGramos;
	}

	public void setPesoGramos(Integer pesoGramos) {
		this.pesoGramos = pesoGramos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}

package co.edu.unbosque.tallerpatrones.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto extends EntidadBase {

	@Column(nullable = false, unique = true, length = 64)
	private String sku;

	@Column(nullable = false, length = 160)
	private String nombre;
	
    @Column(length = 500)
    private String descripcion;  

	@Enumerated(EnumType.STRING)
	private CategoriaProducto categoria;

	@Column(nullable = false, precision = 14, scale = 2)
	private BigDecimal precio;

	@Column(nullable = false)
	private Integer pesoGramos;
	
    @Column(nullable = false)
    private boolean activo = true;  


	
	public Producto(String sku, String nombre, String descripcion, CategoriaProducto categoria, BigDecimal precio,
			Integer pesoGramos, boolean activo) {
		super();
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precio = precio;
		this.pesoGramos = pesoGramos;
		this.activo = activo;
	}

	public Producto() {
		// TODO Auto-generated constructor stub
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

	public CategoriaProducto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProducto categoria) {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}

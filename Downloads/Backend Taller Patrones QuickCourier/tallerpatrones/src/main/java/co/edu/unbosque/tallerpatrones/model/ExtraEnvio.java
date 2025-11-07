package co.edu.unbosque.tallerpatrones.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "extras_envio")
public class ExtraEnvio extends EntidadBase {

    @Column(nullable = false, unique = true, length = 64)
    private String codigo;

    @Column(nullable = false, length = 160)
    private String nombre;
    
    @Column(length = 500)
    private String descripcion; 

    @Enumerated(EnumType.STRING)
    private TipoExtra tipo;

    @Enumerated(EnumType.STRING)
    private TipoPrecioExtra tipoPrecio;

    @Column(nullable = false, precision = 14, scale = 4)
    private BigDecimal valor;
    
    @Column(nullable = false)
    private boolean activo = true;
    
    public ExtraEnvio() {
		// TODO Auto-generated constructor stub
	}


	public ExtraEnvio(String codigo, String nombre, String descripcion, TipoExtra tipo, TipoPrecioExtra tipoPrecio,
			BigDecimal valor, boolean activo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.tipoPrecio = tipoPrecio;
		this.valor = valor;
		this.activo = activo;
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

	public TipoExtra getTipo() {
		return tipo;
	}

	public void setTipo(TipoExtra tipo) {
		this.tipo = tipo;
	}

	public TipoPrecioExtra getTipoPrecio() {
		return tipoPrecio;
	}

	public void setTipoPrecio(TipoPrecioExtra tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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

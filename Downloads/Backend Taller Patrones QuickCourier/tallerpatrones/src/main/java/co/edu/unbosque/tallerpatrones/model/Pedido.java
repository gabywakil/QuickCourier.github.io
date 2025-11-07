package co.edu.unbosque.tallerpatrones.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos", indexes = { @Index(name = "ix_pedidos_usuario", columnList = "usuario_id"),
		@Index(name = "ix_pedidos_estado", columnList = "estado") })
public class Pedido extends EntidadBase {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedidos_usuarios"))
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false, length = 32)
	private EstadoPedido estado = EstadoPedido.CREADO;

	@Embedded
	private Direccion direccionEnvio;

	@Enumerated(EnumType.STRING)
	@Column(name = "zona_envio", nullable = false, length = 32)
	private ZonaEnvio zonaEnvio = ZonaEnvio.ZONA_1;

	@Column(name = "peso_total_gramos", nullable = false)
	private Integer pesoTotalGramos = 0;

	// --- Totales calculados (snapshots) ---
	@Column(nullable = false, precision = 14, scale = 2)
	private BigDecimal subtotal = BigDecimal.ZERO;

	@Column(name = "costo_envio", nullable = false, precision = 14, scale = 2)
	private BigDecimal costoEnvio = BigDecimal.ZERO;

	@Column(name = "costo_extras", nullable = false, precision = 14, scale = 2)
	private BigDecimal costoExtras = BigDecimal.ZERO;

	@Column(name = "impuestos", nullable = false, precision = 14, scale = 2)
	private BigDecimal impuestos = BigDecimal.ZERO;

	@Column(name = "total_general", nullable = false, precision = 14, scale = 2)
	private BigDecimal totalGeneral = BigDecimal.ZERO;

	@Column(name = "moneda", nullable = false, length = 3)
	private String moneda = "COP";

	@Column(name = "referencia_pago", length = 128)
	private String referenciaPago;

	// --- Relaciones con detalles y extras ---

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetallePedido> detalles = new ArrayList<>();

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExtraPedido> extras = new ArrayList<>();

	// --- Constructores ---
	public Pedido() {
	}

	public Pedido(Usuario usuario, Direccion direccionEnvio, ZonaEnvio zonaEnvio) {
		this.usuario = usuario;
		this.direccionEnvio = direccionEnvio;
		this.zonaEnvio = zonaEnvio;
		this.estado = EstadoPedido.CREADO;
	}

	// --- Getters y Setters ---
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public Direccion getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(Direccion direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public ZonaEnvio getZonaEnvio() {
		return zonaEnvio;
	}

	public void setZonaEnvio(ZonaEnvio zonaEnvio) {
		this.zonaEnvio = zonaEnvio;
	}

	public Integer getPesoTotalGramos() {
		return pesoTotalGramos;
	}

	public void setPesoTotalGramos(Integer pesoTotalGramos) {
		this.pesoTotalGramos = pesoTotalGramos;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public BigDecimal getCostoExtras() {
		return costoExtras;
	}

	public void setCostoExtras(BigDecimal costoExtras) {
		this.costoExtras = costoExtras;
	}

	public BigDecimal getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(BigDecimal impuestos) {
		this.impuestos = impuestos;
	}

	public BigDecimal getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(BigDecimal totalGeneral) {
		this.totalGeneral = totalGeneral;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getReferenciaPago() {
		return referenciaPago;
	}

	public void setReferenciaPago(String referenciaPago) {
		this.referenciaPago = referenciaPago;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	public List<ExtraPedido> getExtras() {
		return extras;
	}

	public void setExtras(List<ExtraPedido> extras) {
		this.extras = extras;
	}

	public void agregarDetalle(DetallePedido detalle) {
		detalles.add(detalle);
		detalle.setPedido(this);
	}

	public void eliminarDetalle(DetallePedido detalle) {
		detalles.remove(detalle);
		detalle.setPedido(null);
	}

	public void agregarExtra(ExtraPedido extra) {
		extras.add(extra);
		extra.setPedido(this);
	}

	public void eliminarExtra(ExtraPedido extra) {
		extras.remove(extra);
		extra.setPedido(null);
	}
}

package co.edu.unbosque.tallerpatrones.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "extras_pedido", indexes = { @Index(name = "ix_extras_pedido_pedido", columnList = "pedido_id") })
public class ExtraPedido extends EntidadBase {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_extras_pedido_pedido"))
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "extra_envio_id", nullable = false, foreignKey = @ForeignKey(name = "fk_extras_pedido_extra_envio"))
	private ExtraEnvio extraEnvio;

	@Column(name = "nombre_extra_snapshot", nullable = false, length = 160)
	private String nombreExtraSnapshot;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_precio_snapshot", nullable = false, length = 32)
	private TipoPrecioExtra tipoPrecioSnapshot;

	@Column(name = "valor_snapshot", nullable = false, precision = 14, scale = 4)
	private BigDecimal valorSnapshot;

	@Column(name = "costo_aplicado", nullable = false, precision = 14, scale = 2)
	private BigDecimal costoAplicado;

	public ExtraPedido() {
	}

	public ExtraPedido(Pedido pedido, ExtraEnvio extraEnvio, String nombreExtraSnapshot,
			TipoPrecioExtra tipoPrecioSnapshot, BigDecimal valorSnapshot, BigDecimal costoAplicado) {
		this.pedido = pedido;
		this.extraEnvio = extraEnvio;
		this.nombreExtraSnapshot = nombreExtraSnapshot;
		this.tipoPrecioSnapshot = tipoPrecioSnapshot;
		this.valorSnapshot = valorSnapshot;
		this.costoAplicado = costoAplicado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public ExtraEnvio getExtraEnvio() {
		return extraEnvio;
	}

	public void setExtraEnvio(ExtraEnvio extraEnvio) {
		this.extraEnvio = extraEnvio;
	}

	public String getNombreExtraSnapshot() {
		return nombreExtraSnapshot;
	}

	public void setNombreExtraSnapshot(String nombreExtraSnapshot) {
		this.nombreExtraSnapshot = nombreExtraSnapshot;
	}

	public TipoPrecioExtra getTipoPrecioSnapshot() {
		return tipoPrecioSnapshot;
	}

	public void setTipoPrecioSnapshot(TipoPrecioExtra tipoPrecioSnapshot) {
		this.tipoPrecioSnapshot = tipoPrecioSnapshot;
	}

	public BigDecimal getValorSnapshot() {
		return valorSnapshot;
	}

	public void setValorSnapshot(BigDecimal valorSnapshot) {
		this.valorSnapshot = valorSnapshot;
	}

	public BigDecimal getCostoAplicado() {
		return costoAplicado;
	}

	public void setCostoAplicado(BigDecimal costoAplicado) {
		this.costoAplicado = costoAplicado;
	}

	@Override
	public String toString() {
		return "ExtraPedido{" + "id=" + getId() + ", nombreExtraSnapshot='" + nombreExtraSnapshot + '\''
				+ ", tipoPrecioSnapshot=" + tipoPrecioSnapshot + ", valorSnapshot=" + valorSnapshot + ", costoAplicado="
				+ costoAplicado + '}';
	}
}
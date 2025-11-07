package co.edu.unbosque.tallerpatrones.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "detalles_pedido",
        indexes = {
                @Index(name = "ix_detalles_pedido_pedido", columnList = "pedido_id")
        }
)
public class DetallePedido extends EntidadBase {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "pedido_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_detalles_pedido_pedido")
    )
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "producto_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_detalles_pedido_producto")
    )
    private Producto producto;

    @Column(name = "nombre_producto_snapshot", nullable = false, length = 160)
    private String nombreProductoSnapshot;

    @Column(name = "precio_unitario_snapshot", nullable = false, precision = 14, scale = 2)
    private BigDecimal precioUnitarioSnapshot;

    @Column(name = "peso_gramos_snapshot", nullable = false)
    private Integer pesoGramosSnapshot;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "subtotal_linea", nullable = false, precision = 14, scale = 2)
    private BigDecimal subtotalLinea;

    public DetallePedido() {}

    public DetallePedido(Pedido pedido,
                         Producto producto,
                         String nombreProductoSnapshot,
                         BigDecimal precioUnitarioSnapshot,
                         Integer pesoGramosSnapshot,
                         Integer cantidad,
                         BigDecimal subtotalLinea) {
        this.pedido = pedido;
        this.producto = producto;
        this.nombreProductoSnapshot = nombreProductoSnapshot;
        this.precioUnitarioSnapshot = precioUnitarioSnapshot;
        this.pesoGramosSnapshot = pesoGramosSnapshot;
        this.cantidad = cantidad;
        this.subtotalLinea = subtotalLinea;
    }

    // --- Getters y Setters ---
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public String getNombreProductoSnapshot() { return nombreProductoSnapshot; }
    public void setNombreProductoSnapshot(String nombreProductoSnapshot) { this.nombreProductoSnapshot = nombreProductoSnapshot; }

    public BigDecimal getPrecioUnitarioSnapshot() { return precioUnitarioSnapshot; }
    public void setPrecioUnitarioSnapshot(BigDecimal precioUnitarioSnapshot) { this.precioUnitarioSnapshot = precioUnitarioSnapshot; }

    public Integer getPesoGramosSnapshot() { return pesoGramosSnapshot; }
    public void setPesoGramosSnapshot(Integer pesoGramosSnapshot) { this.pesoGramosSnapshot = pesoGramosSnapshot; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getSubtotalLinea() { return subtotalLinea; }
    public void setSubtotalLinea(BigDecimal subtotalLinea) { this.subtotalLinea = subtotalLinea; }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "id=" + getId() +
                ", producto=" + (producto != null ? producto.getSku() : null) +
                ", cantidad=" + cantidad +
                ", subtotalLinea=" + subtotalLinea +
                '}';
    }
}
package co.edu.unbosque.tallerpatrones.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoResumenDTO {

    private String id;
    private String estado;
    private String correoUsuario;
    private DireccionDTO direccionEnvio;
    private String zonaEnvio;
    private Integer pesoTotalGramos;

    private BigDecimal subtotal;
    private BigDecimal costoEnvio;
    private BigDecimal costoExtras;
    private BigDecimal impuestos;
    private BigDecimal totalGeneral;
    private String moneda;

    private List<DetallePedidoDTO> detalles;
    private List<ExtraPedidoDTO> extras;

    public PedidoResumenDTO() {}

    
    
    public PedidoResumenDTO(String id, String estado, String correoUsuario, DireccionDTO direccionEnvio,
			String zonaEnvio, Integer pesoTotalGramos, BigDecimal subtotal, BigDecimal costoEnvio,
			BigDecimal costoExtras, BigDecimal impuestos, BigDecimal totalGeneral, String moneda,
			List<DetallePedidoDTO> detalles, List<ExtraPedidoDTO> extras) {
		super();
		this.id = id;
		this.estado = estado;
		this.correoUsuario = correoUsuario;
		this.direccionEnvio = direccionEnvio;
		this.zonaEnvio = zonaEnvio;
		this.pesoTotalGramos = pesoTotalGramos;
		this.subtotal = subtotal;
		this.costoEnvio = costoEnvio;
		this.costoExtras = costoExtras;
		this.impuestos = impuestos;
		this.totalGeneral = totalGeneral;
		this.moneda = moneda;
		this.detalles = detalles;
		this.extras = extras;
	}



	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public DireccionDTO getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(DireccionDTO direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getZonaEnvio() {
        return zonaEnvio;
    }

    public void setZonaEnvio(String zonaEnvio) {
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

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }

    public List<ExtraPedidoDTO> getExtras() {
        return extras;
    }

    public void setExtras(List<ExtraPedidoDTO> extras) {
        this.extras = extras;
    }
}

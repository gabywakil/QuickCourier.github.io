package co.edu.unbosque.tallerpatrones.dto;

public class DetallePedidoCrearDTO {

	private String productoId;
	private Integer cantidad;

	public DetallePedidoCrearDTO() {
	}
	
	

	public DetallePedidoCrearDTO(String productoId, Integer cantidad) {
		super();
		this.productoId = productoId;
		this.cantidad = cantidad;
	}



	public String getProductoId() {
		return productoId;
	}

	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}

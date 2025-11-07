package co.edu.unbosque.tallerpatrones.dto;

import java.util.List;

public class PedidoCrearDTO {

    private DireccionDTO direccionEnvio;
    private String zonaEnvio;
    private List<DetallePedidoCrearDTO> detalles;
    private List<String> extrasEnvioIds;

    public PedidoCrearDTO() {}
    
    

    public PedidoCrearDTO(DireccionDTO direccionEnvio, String zonaEnvio, List<DetallePedidoCrearDTO> detalles,
			List<String> extrasEnvioIds) {
		super();
		this.direccionEnvio = direccionEnvio;
		this.zonaEnvio = zonaEnvio;
		this.detalles = detalles;
		this.extrasEnvioIds = extrasEnvioIds;
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

    public List<DetallePedidoCrearDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoCrearDTO> detalles) {
        this.detalles = detalles;
    }

    public List<String> getExtrasEnvioIds() {
        return extrasEnvioIds;
    }

    public void setExtrasEnvioIds(List<String> extrasEnvioIds) {
        this.extrasEnvioIds = extrasEnvioIds;
    }
}

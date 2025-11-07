package co.edu.unbosque.tallerpatrones.dto;

public class DireccionDTO {

	private String calle;
	private String ciudad;
	private String departamento;
	private String pais;
	private String codigoPostal;
	private String referencia;

	public DireccionDTO() {
	}
	
	

	public DireccionDTO(String calle, String ciudad, String departamento, String pais, String codigoPostal,
			String referencia) {
		super();
		this.calle = calle;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
		this.referencia = referencia;
	}



	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}

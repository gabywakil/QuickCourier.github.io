package co.edu.unbosque.tallerpatrones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Embeddable
public class Direccion {

	@NotBlank
	@Size(max = 160)
	@Column(name = "calle", nullable = false, length = 160)
	private String calle;

	@NotBlank
	@Size(max = 100)
	@Column(name = "ciudad", nullable = false, length = 100)
	private String ciudad;

	@Size(max = 100)
	@Column(name = "departamento", length = 100)
	private String departamento;

	@NotBlank
	@Size(max = 100)
	@Column(name = "pais", nullable = false, length = 100)
	private String pais;

	@Size(max = 32)
	@Column(name = "codigo_postal", length = 32)
	private String codigoPostal;

	@Size(max = 200)
	@Column(name = "referencia", length = 200)
	private String referencia;

	public Direccion() {
	}

	public Direccion(String calle, String ciudad, String departamento, String pais, String codigoPostal,
			String referencia) {
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

	@Override
	public String toString() {
		return "Direccion{" + "calle='" + calle + '\'' + ", ciudad='" + ciudad + '\'' + ", departamento='"
				+ departamento + '\'' + ", pais='" + pais + '\'' + ", codigoPostal='" + codigoPostal + '\''
				+ ", referencia='" + referencia + '\'' + '}';
	}
}

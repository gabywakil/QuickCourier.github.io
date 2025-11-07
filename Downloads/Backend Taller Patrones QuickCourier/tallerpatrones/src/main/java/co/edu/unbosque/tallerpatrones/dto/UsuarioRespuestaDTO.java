package co.edu.unbosque.tallerpatrones.dto;

import java.util.List;

public class UsuarioRespuestaDTO {

	private String id;
	private String correo;
	private boolean activo;
	private List<String> roles;

	public UsuarioRespuestaDTO() {
	}
	
	

	public UsuarioRespuestaDTO(String id, String correo, boolean activo, List<String> roles) {
		super();
		this.id = id;
		this.correo = correo;
		this.activo = activo;
		this.roles = roles;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}

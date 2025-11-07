package co.edu.unbosque.tallerpatrones.dto;

public class UsuarioRegistroDTO {

    private String correo;
    private String contrasena;

    public UsuarioRegistroDTO() {}
    
    

    public UsuarioRegistroDTO(String correo, String contrasena) {
		super();
		this.correo = correo;
		this.contrasena = contrasena;
	}



	public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}


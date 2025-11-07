package co.edu.unbosque.tallerpatrones.dto;

public class JwtLoginResponse {

    private String token;
    private UsuarioRespuestaDTO usuario;

    public JwtLoginResponse(String token, UsuarioRespuestaDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public JwtLoginResponse() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioRespuestaDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRespuestaDTO usuario) {
        this.usuario = usuario;
    }
}

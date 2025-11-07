package co.edu.unbosque.tallerpatrones.service;

import co.edu.unbosque.tallerpatrones.dto.UsuarioRegistroDTO;
import co.edu.unbosque.tallerpatrones.dto.UsuarioRespuestaDTO;

public interface UsuarioService {

    UsuarioRespuestaDTO registrar(UsuarioRegistroDTO dto);

    UsuarioRespuestaDTO buscarPorCorreo(String correo);
}

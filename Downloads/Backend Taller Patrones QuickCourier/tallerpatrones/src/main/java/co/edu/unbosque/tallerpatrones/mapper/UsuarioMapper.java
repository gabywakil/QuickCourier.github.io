package co.edu.unbosque.tallerpatrones.mapper;

import co.edu.unbosque.tallerpatrones.dto.UsuarioRegistroDTO;
import co.edu.unbosque.tallerpatrones.dto.UsuarioRespuestaDTO;
import co.edu.unbosque.tallerpatrones.model.Rol;
import co.edu.unbosque.tallerpatrones.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static UsuarioRespuestaDTO toRespuestaDTO(Usuario entidad) {
        if (entidad == null) return null;

        UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO();
        dto.setId(entidad.getId().toString());
        dto.setCorreo(entidad.getCorreo());
        dto.setActivo(entidad.isActivo());

        List<String> nombresRoles = entidad.getRoles()
                .stream()
                .map(Rol::getNombre) 
                .map(Enum::name)
                .collect(Collectors.toList());

        dto.setRoles(nombresRoles);
        return dto;
    }

    public static Usuario fromRegistroDTO(UsuarioRegistroDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());
        return usuario;
    }
}

package co.edu.unbosque.tallerpatrones.mapper;

import co.edu.unbosque.tallerpatrones.dto.DireccionDTO;
import co.edu.unbosque.tallerpatrones.model.Direccion;

public class DireccionMapper {

    private DireccionMapper() {}

    public static Direccion toEntidad(DireccionDTO dto) {
        if (dto == null) return null;

        Direccion entidad = new Direccion();
        entidad.setCalle(dto.getCalle());
        entidad.setCiudad(dto.getCiudad());
        entidad.setDepartamento(dto.getDepartamento());
        entidad.setPais(dto.getPais());
        entidad.setCodigoPostal(dto.getCodigoPostal());
        entidad.setReferencia(dto.getReferencia());
        return entidad;
    }

    public static DireccionDTO toDTO(Direccion entidad) {
        if (entidad == null) return null;

        DireccionDTO dto = new DireccionDTO();
        dto.setCalle(entidad.getCalle());
        dto.setCiudad(entidad.getCiudad());
        dto.setDepartamento(entidad.getDepartamento());
        dto.setPais(entidad.getPais());
        dto.setCodigoPostal(entidad.getCodigoPostal());
        dto.setReferencia(entidad.getReferencia());
        return dto;
    }
}

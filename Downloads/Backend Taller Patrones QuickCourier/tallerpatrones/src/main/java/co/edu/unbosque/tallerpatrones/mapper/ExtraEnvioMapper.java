package co.edu.unbosque.tallerpatrones.mapper;

import co.edu.unbosque.tallerpatrones.dto.ExtraEnvioDTO;
import co.edu.unbosque.tallerpatrones.model.ExtraEnvio;

public class ExtraEnvioMapper {

    private ExtraEnvioMapper() {}

    public static ExtraEnvioDTO toDTO(ExtraEnvio entidad) {
        if (entidad == null) return null;

        ExtraEnvioDTO dto = new ExtraEnvioDTO();
        dto.setId(entidad.getId().toString());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setTipo(entidad.getTipo().name());
        dto.setTipoPrecio(entidad.getTipoPrecio().name());
        dto.setValor(entidad.getValor());
        dto.setActivo(entidad.isActivo());
        return dto;
    }
}

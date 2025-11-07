package co.edu.unbosque.tallerpatrones.mapper;

import co.edu.unbosque.tallerpatrones.dto.ProductoDTO;
import co.edu.unbosque.tallerpatrones.model.Producto;

public class ProductoMapper {

    private ProductoMapper() {}

    public static ProductoDTO toDTO(Producto entidad) {
        if (entidad == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setId(entidad.getId().toString());
        dto.setSku(entidad.getSku());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setCategoria(entidad.getCategoria().name());
        dto.setPrecio(entidad.getPrecio());
        dto.setPesoGramos(entidad.getPesoGramos());
        dto.setActivo(entidad.isActivo());
        return dto;
    }
}


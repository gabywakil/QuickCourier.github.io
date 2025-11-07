package co.edu.unbosque.tallerpatrones.service;

import co.edu.unbosque.tallerpatrones.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> listarProductosActivos();

    ProductoDTO obtenerPorId(String id);
}

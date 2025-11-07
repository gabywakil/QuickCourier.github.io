package co.edu.unbosque.tallerpatrones.service;

import co.edu.unbosque.tallerpatrones.dto.ProductoDTO;
import co.edu.unbosque.tallerpatrones.mapper.ProductoMapper;
import co.edu.unbosque.tallerpatrones.model.Producto;
import co.edu.unbosque.tallerpatrones.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> listarProductosActivos() {
        List<Producto> productos = productoRepository.findByActivoTrue();
        return productos.stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(String id) {
        UUID uuid = UUID.fromString(id);
        Producto producto = productoRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
        return ProductoMapper.toDTO(producto);
    }
}

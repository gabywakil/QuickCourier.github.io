package co.edu.unbosque.tallerpatrones.repository;

import co.edu.unbosque.tallerpatrones.model.CategoriaProducto;
import co.edu.unbosque.tallerpatrones.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    Optional<Producto> findBySku(String sku);

    List<Producto> findByActivoTrue();

    List<Producto> findByCategoriaAndActivoTrue(CategoriaProducto categoria);
}

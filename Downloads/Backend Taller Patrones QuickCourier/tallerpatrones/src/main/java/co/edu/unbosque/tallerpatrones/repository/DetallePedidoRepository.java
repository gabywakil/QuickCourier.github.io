package co.edu.unbosque.tallerpatrones.repository;

import co.edu.unbosque.tallerpatrones.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, UUID> {

    List<DetallePedido> findByPedido_Id(UUID pedidoId);
}

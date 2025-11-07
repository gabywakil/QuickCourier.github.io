package co.edu.unbosque.tallerpatrones.repository;

import co.edu.unbosque.tallerpatrones.model.ExtraPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExtraPedidoRepository extends JpaRepository<ExtraPedido, UUID> {

    List<ExtraPedido> findByPedido_Id(UUID pedidoId);
}

package co.edu.unbosque.tallerpatrones.repository;

import co.edu.unbosque.tallerpatrones.model.EstadoPedido;
import co.edu.unbosque.tallerpatrones.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    List<Pedido> findByUsuario_Id(UUID usuarioId);

    List<Pedido> findByUsuario_CorreoIgnoreCase(String correo);

    List<Pedido> findByEstado(EstadoPedido estado);
}

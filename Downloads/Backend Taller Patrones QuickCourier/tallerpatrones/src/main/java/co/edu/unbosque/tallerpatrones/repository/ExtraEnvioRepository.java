package co.edu.unbosque.tallerpatrones.repository;

import co.edu.unbosque.tallerpatrones.model.ExtraEnvio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExtraEnvioRepository extends JpaRepository<ExtraEnvio, UUID> {

    Optional<ExtraEnvio> findByCodigo(String codigo);

    List<ExtraEnvio> findByActivoTrue();
}

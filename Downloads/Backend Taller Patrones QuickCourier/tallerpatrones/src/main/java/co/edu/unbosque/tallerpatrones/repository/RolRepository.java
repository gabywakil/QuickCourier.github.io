package co.edu.unbosque.tallerpatrones.repository;

import co.edu.unbosque.tallerpatrones.model.NombreRol;
import co.edu.unbosque.tallerpatrones.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RolRepository extends JpaRepository<Rol, UUID> {

    Optional<Rol> findByNombre(NombreRol nombre);
}

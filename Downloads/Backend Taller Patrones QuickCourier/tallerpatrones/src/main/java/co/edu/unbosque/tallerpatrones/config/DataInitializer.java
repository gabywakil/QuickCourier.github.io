package co.edu.unbosque.tallerpatrones.config;

import co.edu.unbosque.tallerpatrones.model.NombreRol;
import co.edu.unbosque.tallerpatrones.model.Rol;
import co.edu.unbosque.tallerpatrones.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initRoles(RolRepository rolRepository) {
        return args -> {
            for (NombreRol nombreRol : NombreRol.values()) {
                rolRepository.findByNombre(nombreRol)
                        .orElseGet(() -> {
                            Rol rol = new Rol();
                            rol.setNombre(nombreRol);
                            return rolRepository.save(rol); 
                        });
            }
        };
    }
}

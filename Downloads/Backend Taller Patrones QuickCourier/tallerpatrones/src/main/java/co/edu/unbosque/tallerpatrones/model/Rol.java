package co.edu.unbosque.tallerpatrones.model;


import jakarta.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "uq_roles_nombre", columnNames = "nombre")
})
public class Rol extends EntidadBase {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private NombreRol nombre;

    public Rol() {}

    public Rol(NombreRol nombre) {
        this.nombre = nombre;
    }

    public NombreRol getNombre() {
        return nombre;
    }

    public void setNombre(NombreRol nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + getId() +
                ", nombre=" + nombre +
                '}';
    }
}
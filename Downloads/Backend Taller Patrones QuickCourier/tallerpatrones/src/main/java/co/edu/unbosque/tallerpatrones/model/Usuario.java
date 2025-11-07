package co.edu.unbosque.tallerpatrones.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios",
       uniqueConstraints = @UniqueConstraint(name = "uq_usuarios_correo", columnNames = "correo"))
public class Usuario extends EntidadBase {

    @Column(nullable = false, length = 120)
    private String correo;

    @Column(nullable = false, length = 120)
    private String contrasenaHash;

    @Column(nullable = false)
    private boolean activo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();


    public Usuario() {}

    public Usuario(String correo, String contrasenaHash) {
        this.correo = correo;
        this.contrasenaHash = contrasenaHash;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo == null ? null : correo.trim().toLowerCase();
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + getId() +
                ", correo='" + correo + '\'' +
                ", activo=" + activo +
                ", roles=" + roles +
                '}';
    }
}

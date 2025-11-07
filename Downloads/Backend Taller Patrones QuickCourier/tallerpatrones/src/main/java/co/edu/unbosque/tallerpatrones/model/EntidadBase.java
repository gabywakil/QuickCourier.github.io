package co.edu.unbosque.tallerpatrones.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class EntidadBase {

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private UUID id;

	@Column(nullable = false, updatable = false)
	private OffsetDateTime fechaCreacion;

	@Column(nullable = false)
	private OffsetDateTime fechaActualizacion;

	@PrePersist
	protected void alCrear() {
		this.fechaCreacion = OffsetDateTime.now();
		this.fechaActualizacion = this.fechaCreacion;
	}

	@PreUpdate
	protected void alActualizar() {
		this.fechaActualizacion = OffsetDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public OffsetDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(OffsetDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public OffsetDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(OffsetDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}
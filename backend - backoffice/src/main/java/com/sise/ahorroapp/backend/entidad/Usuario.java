package com.sise.ahorroapp.backend.entidad;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String correo;

	private String clave;

	@Column(nullable = false)
	private Boolean activo = true;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Movimiento> movimientos;

	// Constructor vacío obligatorio
	public Usuario() {
	}

	// Constructor con campos
	public Usuario(String nombre, String correo, String clave) {
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
	}

	// Getters y Setters

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Boolean getActivo() {
	    return activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}

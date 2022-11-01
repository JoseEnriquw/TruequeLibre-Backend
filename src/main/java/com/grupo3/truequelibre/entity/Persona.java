package com.grupo3.truequelibre.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="persona")
public class Persona {
	
	@Id
	private String dni;
	@Column(nullable= false)
	private String nombre;
	@Column(nullable= false)
	private String apellido;
	@Column
	private String direccion;
	@Column(nullable= false)
	private Date fechaNacimiento;
	@Column(nullable= false)
	private String telefono;
	@ManyToOne (cascade= {CascadeType. ALL})	
	@JoinColumn
	private Localidad localidad;
    @OneToOne(mappedBy = "persona")
    private Usuario usuario;

	public Persona() {
	}

	public Persona(String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String telefono,
			Localidad localidad,Usuario usuario) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.localidad = localidad;
		this.usuario=usuario;
	}

	public String getId() {
		return dni;
	}

	public void setId(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
}

package com.grupo3.truequelibre.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

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
    
    @Type(type="org.hibernate.type.BinaryType")
	@Column
	private byte[] imagenes;

	public Persona() {
	}

	
	
	public Persona(String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String telefono,
			Localidad localidad, byte[] imagenes) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.localidad = localidad;
		this.imagenes = imagenes;
	}

	public Persona(String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String telefono,
			Localidad localidad) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.localidad = localidad;
		
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



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public byte[] getImagenes() {
		return imagenes;
	}



	public void setImagenes(byte[] imagenes) {
		this.imagenes = imagenes;
	}
	
	
}

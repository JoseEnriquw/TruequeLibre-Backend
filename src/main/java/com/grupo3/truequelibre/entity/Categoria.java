package com.grupo3.truequelibre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	@Column
	private String descripcion;
	@Lob
	@Column
	private byte[] imagen;
	
	public Categoria() {
		
	}

	public Categoria(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Categoria(String descripcion, byte[] imagen) {
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
	
	
}


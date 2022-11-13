package com.grupo3.truequelibre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="categoria")
public class Categoria {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	@Column
	private String descripcion;
	
	@Type(type="org.hibernate.type.BinaryType")
	@Column
	private byte[] imagenes;

	
	public Categoria() {
		
	}

	public Categoria(String descripcion, byte[] imagenes) {
		this.descripcion = descripcion;
		this.imagenes = imagenes;
	}

	public Categoria(String descripcion) {
		this.descripcion = descripcion;
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

	public byte[] getImagenes() {
		return imagenes;
	}

	public void setImagenes(byte[] imagenes) {
		this.imagenes = imagenes;
	}

	
	
}


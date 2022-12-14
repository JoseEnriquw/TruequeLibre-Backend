package com.grupo3.truequelibre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estado")
public class Estado {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	@Column
	private String descripcion;
	
	public Estado() {
		
	}

	public Estado(Integer id, String descripcion) {		
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Estado(Integer id) {
		this.id = id;
	}

	public Estado(String descripcion) {
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
}

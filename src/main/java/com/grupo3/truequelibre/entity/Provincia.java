package com.grupo3.truequelibre.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="provincia")
public class Provincia {
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY) 
		private Integer id;
		@Column
		private String nombre;
		@ManyToOne (cascade= {CascadeType. ALL})
		@JoinColumn
		private Pais pais;

		public Provincia() {
		}

		public Provincia(Integer id, String nombre, Pais pais) {
			this.id = id;
			this.nombre = nombre;
			this.pais = pais;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Pais getPais() {
			return pais;
		}

		public void setPais(Pais pais) {
			this.pais = pais;
		}
}

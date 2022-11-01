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
@Table(name="localidad")
public class Localidad {
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY) 
		private Integer id;
		@Column
		private String nombre;
		@ManyToOne (cascade= {CascadeType. ALL})
		@JoinColumn
		private Provincia provincia;

		public Localidad() {
		}

		public Localidad(Integer id, String nombre, Provincia provincia) {
			this.id = id;
			this.nombre = nombre;
			this.provincia = provincia;
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

		public Provincia getProvincia() {
			return provincia;
		}

		public void setProvincia(Provincia provincia) {
			this.provincia = provincia;
		}
}

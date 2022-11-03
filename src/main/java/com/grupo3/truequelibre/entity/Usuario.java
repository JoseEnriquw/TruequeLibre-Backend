package com.grupo3.truequelibre.entity;

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
@Table(name="usuario")
public class Usuario {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	@Column(unique=true)
	private String mail;
	@Column(unique=true)
	private String contrasenia;
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Estado estado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dni", referencedColumnName = "dni")
	private Persona persona;

	public Usuario() {
	}
	
	

	public Usuario(Integer id) {
		super();
		this.id = id;
	}



	public Usuario(Integer id, String mail, String contrasenia, Estado estado, Persona persona) {
		this.id = id;
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.persona = persona;
	}
	
	public Usuario(String mail, String contrasenia, Estado estado, Persona persona) {
		
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.persona = persona;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}

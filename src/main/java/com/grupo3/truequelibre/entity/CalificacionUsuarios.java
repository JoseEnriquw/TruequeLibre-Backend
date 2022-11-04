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
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table(name="calificacion_usuarios")
public class CalificacionUsuarios {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	@Column
	@Check(constraints = "estado >=1  AND estado <= 5")
	private short estrellas;
	@Column
	private String comentario;
	@Column
	private Date fecha;
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn
	private Usuario usuario;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn
	private Usuario usuarioCalificador;
	
	public CalificacionUsuarios() {
	}

	public CalificacionUsuarios(Integer id, short estrellas, String comentario, Date fecha, Usuario usuario) {
		this.id = id;
		this.estrellas = estrellas;
		this.comentario = comentario;
		this.fecha = fecha;
		this.usuario = usuario;
	}
	
	public CalificacionUsuarios(short estrellas, String comentario, Date fecha, Usuario usuario, Usuario usuarioCalificador) {
		this.estrellas = estrellas;
		this.comentario = comentario;
		this.fecha = fecha;
		this.usuario = usuario;
		this.usuarioCalificador = usuarioCalificador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(short estrellas) {
		this.estrellas = estrellas;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
	public Usuario getUsuarioCalificador() {
		return usuarioCalificador;
	}

	public void setUsuarioCalificador(Usuario usuarioCalificador) {
		this.usuarioCalificador = usuarioCalificador;
	}
}

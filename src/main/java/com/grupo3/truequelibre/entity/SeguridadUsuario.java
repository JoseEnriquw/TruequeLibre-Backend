package com.grupo3.truequelibre.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name ="seguridadUsuario")
public class SeguridadUsuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String token;
	
	@CreationTimestamp
	@Column(updatable=false)
	private Timestamp timeStamp;
	
	@Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expira;
	
	@ManyToOne(cascade= {CascadeType. ALL})
	@JoinColumn
	private Usuario usuario;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Estado estado;
	
	@Transient
	private boolean isExpired;
	
	public SeguridadUsuario(String token, Timestamp timeStamp, LocalDateTime expira, Usuario usuario) {
		this.token = token;
		this.timeStamp = timeStamp;
		this.expira = expira;
		this.usuario = usuario;
	}
	
	public SeguridadUsuario() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public LocalDateTime getExpira() {
		return expira;
	}

	public void setExpira(LocalDateTime expira) {
		this.expira = expira;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isExpired() {
		return getExpira().isBefore(LocalDateTime.now());
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
	
}

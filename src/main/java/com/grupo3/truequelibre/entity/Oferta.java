package com.grupo3.truequelibre.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="oferta")
public class Oferta {
	
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@EmbeddedId
	private PublicacionesOfertasID idPublicacionesOfertas;
	
	@ManyToOne
	@MapsId("idPublicacionPrincipal")
	@JoinColumn(name = "id_publicacion_principal")
	private Publicacion publicacionPrincipal;
	
	@ManyToOne
	@MapsId("idPublicacionOfertante")
	@JoinColumn(name = "id_publicacion_oferante")
	private Publicacion publicacionOferante;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Estado estado;

	public Oferta(Integer idOferta, PublicacionesOfertasID id, Publicacion publicacionPrincipal,
			Publicacion publicacionOferante, Estado estado) {
		super();
		this.id = idOferta;
		this.idPublicacionesOfertas = id;
		this.publicacionPrincipal = publicacionPrincipal;
		this.publicacionOferante = publicacionOferante;
		this.estado = estado;
	}
	
	public Oferta() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PublicacionesOfertasID getIdPublicacionesOfertas() {
		return idPublicacionesOfertas;
	}

	public void setIdPublicacionesOfertas(PublicacionesOfertasID idPublicacionesOfertas) {
		this.idPublicacionesOfertas = idPublicacionesOfertas;
	}

	public Publicacion getPublicacionPrincipal() {
		return publicacionPrincipal;
	}

	public void setPublicacionPrincipal(Publicacion publicacionPrincipal) {
		this.publicacionPrincipal = publicacionPrincipal;
	}

	public Publicacion getPublicacionOferante() {
		return publicacionOferante;
	}

	public void setPublicacionOferante(Publicacion publicacionOferante) {
		this.publicacionOferante = publicacionOferante;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}

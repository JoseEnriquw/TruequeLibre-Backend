package com.grupo3.truequelibre.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="oferta")
public class Oferta {
	
	@Column(columnDefinition = "INT NOT NULL UNIQUE KEY auto_increment")
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
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(nullable=false)
	private Estado estado;
	
    @Column(nullable=false,columnDefinition="bit default 0")
	private boolean usuario_principal_acepto;
	
    @Column(nullable=false,columnDefinition="bit default 0")
	private boolean usuario_ofertante_acepto;
    
    @Column(nullable=false,columnDefinition="bit default 0")
  	private boolean usuario_principal_califico;
  	
     @Column(nullable=false,columnDefinition="bit default 0")
  	private boolean usuario_ofertante_califico;
	




	public boolean isUsuario_principal_califico() {
		return usuario_principal_califico;
	}



	public void setUsuario_principal_califico(boolean usuario_principal_califico) {
		this.usuario_principal_califico = usuario_principal_califico;
	}



	public boolean isUsuario_ofertante_califico() {
		return usuario_ofertante_califico;
	}



	public void setUsuario_ofertante_califico(boolean usuario_ofertante_califico) {
		this.usuario_ofertante_califico = usuario_ofertante_califico;
	}



	public Oferta(Integer idOferta, PublicacionesOfertasID id, Publicacion publicacionPrincipal,
			Publicacion publicacionOferante, Estado estado) {
		super();
		this.id = idOferta;
		this.idPublicacionesOfertas = id;
		this.publicacionPrincipal = publicacionPrincipal;
		this.publicacionOferante = publicacionOferante;
		this.estado = estado;
	}
	
	
	
	public Oferta(Integer id, PublicacionesOfertasID idPublicacionesOfertas, Publicacion publicacionPrincipal,
			Publicacion publicacionOferante, Estado estado, boolean usuario_principal_acepto,
			boolean usuario_ofertante_acepto, boolean usuario_principal_califico, boolean usuario_ofertante_califico) {
		super();
		this.id = id;
		this.idPublicacionesOfertas = idPublicacionesOfertas;
		this.publicacionPrincipal = publicacionPrincipal;
		this.publicacionOferante = publicacionOferante;
		this.estado = estado;
		this.usuario_principal_acepto = usuario_principal_acepto;
		this.usuario_ofertante_acepto = usuario_ofertante_acepto;
		this.usuario_principal_califico = usuario_principal_califico;
		this.usuario_ofertante_califico = usuario_ofertante_califico;
	}



	public Oferta(PublicacionesOfertasID idPublicacionesOfertas, Estado estado) {
		super();
		this.idPublicacionesOfertas = idPublicacionesOfertas;
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
	
	public boolean isUsuario_principal_acepto() {
		return usuario_principal_acepto;
	}


	public void setUsuario_principal_acepto(boolean usuario_principal_acepto) {
		this.usuario_principal_acepto = usuario_principal_acepto;
	}


	public boolean isUsuario_ofertante_acepto() {
		return usuario_ofertante_acepto;
	}


	public void setUsuario_ofertante_acepto(boolean usuario_ofertante_acepto) {
		this.usuario_ofertante_acepto = usuario_ofertante_acepto;
	}
}

package com.grupo3.truequelibre.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class PublicacionesOfertasID implements Serializable {
	
	@Column(name = "id_publicacion_principal")
	private Integer idPublicacionPrincipal;
	
	@Column(name = "id_publicacion_oferante")
	private Integer idPublicacionOfertante;
	
	public PublicacionesOfertasID(Integer idPublicacionPrincipal, Integer idPublicacionOfertante) {
		super();
		this.idPublicacionPrincipal = idPublicacionPrincipal;
		this.idPublicacionOfertante = idPublicacionOfertante;
	}

	public PublicacionesOfertasID() {}
	
	
	public Integer getIdPublicacionPrincipal() {
		return idPublicacionPrincipal;
	}

	public void setIdPublicacionPrincipal(Integer idPublicacionPrincipal) {
		this.idPublicacionPrincipal = idPublicacionPrincipal;
	}

	public Integer getIdPublicacionOfertante() {
		return idPublicacionOfertante;
	}

	public void setIdPublicacionOfertante(Integer idPublicacionOfertante) {
		this.idPublicacionOfertante = idPublicacionOfertante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPublicacionOfertante, idPublicacionPrincipal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicacionesOfertasID other = (PublicacionesOfertasID) obj;
		return Objects.equals(idPublicacionOfertante, other.idPublicacionOfertante)
				&& Objects.equals(idPublicacionPrincipal, other.idPublicacionPrincipal);
	}
	
	
	
}

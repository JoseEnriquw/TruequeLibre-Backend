package com.grupo3.truequelibre.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="finalizar_trueque")
public class finalizar_trueque {
	@Column(columnDefinition = "INT NOT NULL UNIQUE KEY auto_increment")
	private Integer id;
	
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Oferta id_oferta;  
	
	@Column(nullable=false)
	private Boolean usuario_1_acepto;
	
	@Column(nullable=false)
	private Boolean usuario_2_acepto;

	public Oferta getId_oferta() {
		return id_oferta;
	}

	public void setId_oferta(Oferta id_oferta) {
		this.id_oferta = id_oferta;
	}

	public Boolean getUsuario_1_acepto() {
		return usuario_1_acepto;
	}

	public void setUsuario_1_acepto(Boolean usuario_1_acepto) {
		this.usuario_1_acepto = usuario_1_acepto;
	}

	public Boolean getUsuario_2_acepto() {
		return usuario_2_acepto;
	}

	public void setUsuario_2_acepto(Boolean usuario_2_acepto) {
		this.usuario_2_acepto = usuario_2_acepto;
	}

	public finalizar_trueque(Integer id, Oferta id_oferta, Boolean usuario_1_acepto, Boolean usuario_2_acepto) {
		super();
		this.id = id;
		this.id_oferta = id_oferta;
		this.usuario_1_acepto = usuario_1_acepto;
		this.usuario_2_acepto = usuario_2_acepto;
	}	
	
	

}

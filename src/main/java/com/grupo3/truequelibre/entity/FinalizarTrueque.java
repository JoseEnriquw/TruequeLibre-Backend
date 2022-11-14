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
@Table(name="FinalizarTrueque")
public class FinalizarTrueque {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;	

	@Column(nullable=false)
	private Integer id_oferta;  
	
	@Column(nullable=false)
	private boolean usuario_principal_acepto;
	
	@Column(nullable=false)
	private boolean usuario_ofertante_acepto;
	
	
	public Integer getId() {
		return id;
	}

	

	public Oferta getId_oferta() {
		return id_oferta;
	}



	public void setId_oferta(Oferta id_oferta) {
		this.id_oferta = id_oferta;
	}



	public boolean getUsuario_principal_acepto() {
		return usuario_principal_acepto;
	}



	public void setUsuario_principal_acepto(boolean usuario_principal_acepto) {
		this.usuario_principal_acepto = usuario_principal_acepto;
	}



	public boolean getUsuario_ofertante_acepto() {
		return usuario_ofertante_acepto;
	}



	public void setUsuario_ofertante_acepto(boolean usuario_ofertante_acepto) {
		this.usuario_ofertante_acepto = usuario_ofertante_acepto;
	}



	public FinalizarTrueque( Oferta id_oferta, boolean usuario_1_acepto, boolean usuario_2_acepto) {
		super();		
		this.id_oferta = id_oferta;
		this.usuario_principal_acepto = usuario_1_acepto;
		this.usuario_ofertante_acepto = usuario_2_acepto;
	}	
	
	

}

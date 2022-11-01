package com.grupo3.truequelibre.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="publicacion")
public class Publicacion {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Usuario usuario;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column
	private String descripcion;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Categoria categoria;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn
	private Categoria categoriaPretendida;
	@Lob
	@Column
	private byte[] imagenes;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Condicion condicion;
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Localidad ubicacion;  	
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Localidad ubicacionPretendida;  
	
	@ManyToOne (cascade= {CascadeType. ALL})
	@JoinColumn(nullable=false)
	private Estado estado; 
	
	@OneToMany(mappedBy = "publicacionOferante")
	private Set<Oferta> ofertasEnviadas;
	
	@OneToMany(mappedBy = "publicacionPrincipal")
	private Set<Oferta> ofertasRecibidas;
	
	public Publicacion() {}
	
	

	public Publicacion(Integer id, Usuario usuario, String nombre, String descripcion, Categoria categoria,
			Categoria categoriaPretendida, byte[] imagenes, Condicion condicion, Localidad ubicacion,
			Localidad ubicacionPretendida, Estado estado) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.categoriaPretendida = categoriaPretendida;
		this.imagenes = imagenes;
		this.condicion = condicion;
		this.ubicacion = ubicacion;
		this.ubicacionPretendida = ubicacionPretendida;
		this.estado = estado;
		
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoriaPretendida() {
		return categoriaPretendida;
	}

	public void setCategoriaPretendida(Categoria categoriaPretendida) {
		this.categoriaPretendida = categoriaPretendida;
	}

	public byte[] getImagenes() {
		return imagenes;
	}

	public void setImagenes(byte[] imagenes) {
		this.imagenes = imagenes;
	}

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Localidad getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Localidad ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Localidad getUbicacionPretendida() {
		return ubicacionPretendida;
	}

	public void setUbicacionPretendida(Localidad ubicacionPretendida) {
		this.ubicacionPretendida = ubicacionPretendida;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
	

}

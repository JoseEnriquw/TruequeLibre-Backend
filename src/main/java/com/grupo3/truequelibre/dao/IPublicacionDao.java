package com.grupo3.truequelibre.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo3.truequelibre.entity.Publicacion;

public interface IPublicacionDao extends JpaRepository<Publicacion,Integer>{

	List<Publicacion> findByEstadoIdNotAndUsuarioId(Integer estado,Integer usuario);
	List<Publicacion> findByEstadoIdNotAndCategoriaIdAndUsuarioIdNot(Integer estado,Integer categoria,Integer usuario);
	@Query(value="select pu.* from publicacion pu inner join usuario usu on usu.id=pu.usuario_id inner join persona per on usu.dni=per.dni where (per.nombre like %:nombre% or per.apellido like %:nombre% or pu.nombre like %:nombre% or pu.descripcion like %:nombre%) and pu.categoria_id=:categoria and pu.usuario_id!=:usuario and pu.estado_id <>2", nativeQuery=true)
	List<Publicacion> findByCategoriaIdNombreAndUsuarioIdNotIncompleto(Integer categoria,String nombre,Integer usuario);
}

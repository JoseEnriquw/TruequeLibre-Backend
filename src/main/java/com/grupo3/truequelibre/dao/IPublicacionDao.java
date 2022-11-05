package com.grupo3.truequelibre.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.truequelibre.entity.Publicacion;

public interface IPublicacionDao extends JpaRepository<Publicacion,Integer>{

	List<Publicacion> findByEstadoIdNot(Integer estado);
	List<Publicacion> findByCategoriaId(Integer categoria);
}

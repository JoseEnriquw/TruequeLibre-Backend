package com.grupo3.truequelibre.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo3.truequelibre.entity.SeguridadUsuario;

public interface ISeguridadUsuarioDao extends JpaRepository<SeguridadUsuario,Integer>{
	
	Optional<SeguridadUsuario> findByTokenAndEstadoIdNot(String token, Integer estado);
}

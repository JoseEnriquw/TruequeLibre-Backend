package com.grupo3.truequelibre.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.truequelibre.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario,Integer>{
	
	List<Usuario> findByEstadoIdNot(Integer estado);
	
	Optional<Usuario> findByMailAndEstadoIdNot(String mail, Integer estado );
}

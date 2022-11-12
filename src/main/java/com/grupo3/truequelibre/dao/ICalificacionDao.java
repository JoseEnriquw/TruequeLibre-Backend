package com.grupo3.truequelibre.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo3.truequelibre.entity.CalificacionUsuarios;

@Repository
@Transactional
public interface ICalificacionDao extends JpaRepository<CalificacionUsuarios,Integer>{
	List<CalificacionUsuarios> findByUsuarioId(Integer usuario);
	Optional<List<CalificacionUsuarios>> findByEstrellasGreaterThanEqual(short estrellas);
	Optional<List<CalificacionUsuarios>> findByEstrellasLessThanEqual(short estrellas);
}

package com.grupo3.truequelibre.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo3.truequelibre.entity.Oferta;

@Repository
@Transactional
public interface IOfertasDao extends JpaRepository<Oferta,Integer>{
	
	
	@Query("select * from oferta where id = :idOferta ")
	Optional<Oferta> findById(@Param("idOferta") Integer idOferta);
	

}

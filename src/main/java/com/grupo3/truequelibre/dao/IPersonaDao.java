package com.grupo3.truequelibre.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.entity.Persona;

@Repository
@Transactional
public interface IPersonaDao extends JpaRepository<Persona,Integer>{
	
	@Query(value="select p.* from persona p join Usuario on Usuario.dni=p.dni  where Usuario.id= :id ", nativeQuery=true)
	Optional<Persona> findById(@Param("id") Integer id);

}

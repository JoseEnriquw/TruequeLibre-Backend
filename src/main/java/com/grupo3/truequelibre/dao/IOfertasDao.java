package com.grupo3.truequelibre.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import org.springframework.data.jpa.repository.Query;



import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.entity.Usuario;

@Repository
@Transactional
public interface IOfertasDao extends JpaRepository<Oferta,Integer>{
	
	List<Oferta> findByEstadoIdNot(Integer estado);
	
	@Query(value="select * from oferta where id = :idOferta ", nativeQuery=true)
	Optional<Oferta> findById(@Param("idOferta") Integer idOferta);

	Optional<List<Oferta>> findByEstadoIdAndPublicacionPrincipal_Usuario(Integer idEstado,@Param("idUsuario") Usuario idUsuario);
	Optional<List<Oferta>> findByEstadoIdAndPublicacionOferante_Usuario( Integer idEstado,@Param("idUsuarioOfertante") Usuario idUsuarioOfertante);
	Optional<List<Oferta>> findByEstadoIdAndPublicacionOferante_UsuarioOrPublicacionPrincipal_Usuario(Integer idEstado,@Param("idUsuarioOfertante") Usuario idUsuarioOfertante,@Param("idUsuario") Usuario idUsuario);
	
	

}
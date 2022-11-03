package com.grupo3.truequelibre.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.entity.Usuario;

@Repository
@Transactional
public interface IOfertasDao extends JpaRepository<Oferta,Integer>{
	
	
	@Query("select * from oferta where id = :idOferta ")
	Optional<Oferta> findById(@Param("idOferta") Integer idOferta);
	
	
//	Optional<List<Oferta>> findByEstadoOrPublicacionPrincipal_Usuario(@Param("idEstado") Estado idEstado,@Param("idUsuario") Usuario idUsuario);
	Optional<List<Oferta>> findByEstadoAndPublicacionPrincipal_Usuario(@Param("idEstado") Estado idEstado,@Param("idUsuario") Usuario idUsuario);
	Optional<List<Oferta>> findByEstadoAndPublicacionOferante_Usuario(@Param("idEstado") Estado idEstado,@Param("idUsuarioOfertante") Usuario idUsuarioOfertante);

	Optional<List<Oferta>> findByEstado_idAndPublicacionPrincipal_UsuarioOrPublicacionOferante_Usuario(@Param("Estado_id") Integer Estado_id,@Param("idUsuarioPrincipal") Usuario idUsuarioPrincipal,@Param("idUsuarioOfertante") Usuario idUsuarioOfertante);
	
//	@Query("select * from oferta ofer inner join publicacion p_principal on p_principal.id = ofer.id_publicacion_principal inner join publicacion p_ofertante on p_ofertante.id = ofer.id_publicacion_oferante" 
//	+ " where estado_id = %?1%")
//	Optional<List<Oferta>> findAll(Integer estado);
	

}
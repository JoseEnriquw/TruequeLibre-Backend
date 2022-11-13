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
import com.grupo3.truequelibre.entity.finalizar_trueque;

@Repository
@Transactional
public interface IOfertasDao extends JpaRepository<Oferta,Integer>{
	
	List<Oferta> findByEstadoIdNot(Integer estado);
	
	@Query(value="select * from oferta where id = :idOferta ", nativeQuery=true)
	Optional<Oferta> findById(@Param("idOferta") Integer idOferta);

	Optional<List<Oferta>> findByEstadoIdAndPublicacionPrincipal_UsuarioId(Integer idEstado, Integer idUsuario);
	Optional<List<Oferta>> findByEstadoIdAndPublicacionOferante_Usuario( Integer idEstado,@Param("idUsuarioOfertante") Usuario idUsuarioOfertante);


	@Query(value="select oft.* from truequelibredb.oferta oft left join publicacion p on p.id=oft.id_publicacion_principal left join estado es on es.id=oft.estado_id where p.usuario_id = :idUsuario and (es.id = :idEstado or es.id = :idEstado2) union select oft.* from truequelibredb.oferta oft left join publicacion p on p.id=oft.id_publicacion_oferante left join estado es on es.id=oft.estado_id where p.usuario_id = :idUsuario and (es.id = :idEstado or es.id = :idEstado2 )",nativeQuery=true)
	Optional<List<Oferta>> findByAllOf(Integer idEstado,Integer idEstado2, Integer idUsuario);
	
	@Query(value="SELECT oft.* FROM oferta oft join publicacion p on p.id= oft.id_publicacion_principal where  oft.id != :idOferta and ((oft.id_publicacion_oferante= :idPublicacionPrincipal or oft.id_publicacion_principal= :idPublicacionPrincipal ) or (oft.id_publicacion_oferante= :idPublicacionOfertante or oft.id_publicacion_principal= :idPublicacionOfertante))",nativeQuery=true)
	Optional<List<Oferta>> findByAll(Integer idOferta, Integer idPublicacionPrincipal, Integer idPublicacionOfertante);
	
	@Query(value="select id, id_oferta, usuario_1_acepto, usuario_2_acepto from finalizar_trueque where id_oferta =:idOferta", nativeQuery=true)
	Optional<List<finalizar_trueque>> finByAll(Integer idOferta);

}
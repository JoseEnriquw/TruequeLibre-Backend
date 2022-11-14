package com.grupo3.truequelibre.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo3.truequelibre.entity.FinalizarTrueque;


@Repository
@Transactional
public interface IFinalizarTruequeDao extends JpaRepository<FinalizarTrueque,Integer>{
	@Query(value="select id, id_oferta, usuario_1_acepto, usuario_2_acepto from finalizar_trueque where id_oferta =:idOferta", nativeQuery=true)
	Optional<FinalizarTrueque> finByAll(Integer idOferta);

}

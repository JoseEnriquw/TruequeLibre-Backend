package com.grupo3.truequelibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo3.truequelibre.entity.Oferta;

public interface IOfertasDao extends JpaRepository<Oferta,Integer>{
	
}

package com.grupo3.truequelibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.truequelibre.entity.Categoria;

public interface ICategoriaDao extends JpaRepository<Categoria,Integer> {

}

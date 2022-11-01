package com.grupo3.truequelibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo3.truequelibre.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario,Integer>{

}

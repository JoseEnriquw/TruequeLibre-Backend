package com.grupo3.truequelibre.services;

import com.grupo3.truequelibre.interfaces.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo3.truequelibre.dao.IEstadoDao;
import com.grupo3.truequelibre.entity.*;

@Service
public class EstadoServices implements IGenericServices<Estado,Integer> {
    
	@Autowired
	private IEstadoDao estadoDao;
	
	@Override
	public List<Estado> getAll() {
		return (List<Estado>)estadoDao.findAll();
	}

	@Override
	public Estado getById(Integer id) {
		return (estadoDao.findById(id)).orElse(null);
	}

	@Override
	public void save(Estado entity) {
	    estadoDao.save(entity);	
	}

	@Override
	public void delete(Integer id) {
	   estadoDao.deleteById(id);	
	}

}

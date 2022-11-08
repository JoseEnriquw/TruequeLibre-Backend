package com.grupo3.truequelibre.services.Categoria;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.grupo3.truequelibre.dao.ICategoriaDao;
import com.grupo3.truequelibre.entity.Categoria;
import com.grupo3.truequelibre.interfaces.ICategoriaServices;
import com.grupo3.truequelibre.tools.Response;

@Service
@Validated
public class CategoriaServices implements ICategoriaServices {
	@Autowired
	ICategoriaDao categoriaDao;
	
	@Override
	public Response<List<Categoria>> getAll() {
		return new Response<>(categoriaDao.findAll(),HttpStatus.OK);
	}

	@Override
	public Response<Categoria> getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<?> create(@Valid CreateCategoriaRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<?> update(@Valid UpdateCategoriaRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<?> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

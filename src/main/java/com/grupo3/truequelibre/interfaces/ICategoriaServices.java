package com.grupo3.truequelibre.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.grupo3.truequelibre.entity.Categoria;
import com.grupo3.truequelibre.services.CategoriaService.CreateCategoriaRequest;
import com.grupo3.truequelibre.services.CategoriaService.UpdateCategoriaRequest;
import com.grupo3.truequelibre.tools.Response;


public interface ICategoriaServices {

	Response<List<Categoria>> getAll();
	Response<Categoria> getById(Integer id);
	Response<?> create(@Valid CreateCategoriaRequest request);
	Response<?> update(@Valid UpdateCategoriaRequest request);
	Response<?> delete(Integer id);
	Response<List<Categoria>> cargarImagenes();
}

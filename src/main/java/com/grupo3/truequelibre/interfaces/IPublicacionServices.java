package com.grupo3.truequelibre.interfaces;

import java.util.List;

import com.grupo3.truequelibre.entity.Publicacion;
import com.grupo3.truequelibre.tools.Response;

public interface IPublicacionServices {
	Response<List<Publicacion>> getAll();
	Response<Publicacion> getById(Integer id);
	
}

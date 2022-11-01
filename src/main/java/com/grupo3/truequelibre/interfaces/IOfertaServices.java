package com.grupo3.truequelibre.interfaces;

import java.util.List;

import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.tools.Response;

public interface IOfertaServices {

	Response<List<Oferta>> getAll();
	Response<Oferta> getById(Integer id);
	
}

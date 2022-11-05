package com.grupo3.truequelibre.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.services.OfertaService.CreateOfertaRequest;
import com.grupo3.truequelibre.services.OfertaService.FiltrarOfertaRequest;
import com.grupo3.truequelibre.services.OfertaService.UpdateOfertaRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IOfertaServices {

	Response<List<Oferta>> getAll();
	Response<Oferta> getById(Integer id);
	Response<?> create(@Valid CreateOfertaRequest request);
	Response<?> update(@Valid UpdateOfertaRequest request);
	Response<?> delete(Integer id);
	Response<List<Oferta>> filtrar(@Valid FiltrarOfertaRequest request);

}

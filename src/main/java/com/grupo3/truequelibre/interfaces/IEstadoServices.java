package com.grupo3.truequelibre.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.services.EstadoService.CreateEstado;
import com.grupo3.truequelibre.services.EstadoService.UpdateEstado;
import com.grupo3.truequelibre.tools.Response;

public interface IEstadoServices {
 
	Response<List<Estado>> getAll();
	Response<Estado> getById(Integer id);
	Response create(CreateEstado request);
	Response update(@Valid UpdateEstado request);
	Response delete(Integer id);
	
}

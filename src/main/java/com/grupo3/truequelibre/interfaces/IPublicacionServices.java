package com.grupo3.truequelibre.interfaces;

import java.util.List;
import javax.validation.Valid;

import com.grupo3.truequelibre.responses.Publicacion.PublicacionDropdownResponse;
import com.grupo3.truequelibre.responses.Publicacion.PublicacionResponse;
import com.grupo3.truequelibre.services.PublicacionService.CreatePublicacionRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetAllByCategoriaFilterRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetAllByCategoriaRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetByIdRequest;
import com.grupo3.truequelibre.services.PublicacionService.UpdatePublicacionRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IPublicacionServices {
	Response<List<PublicacionResponse>> getAll();
	Response<List<PublicacionResponse>> getAllByCategoria(@Valid GetAllByCategoriaRequest request);
	Response<List<PublicacionResponse>> getAllByCategoriaFilter(@Valid GetAllByCategoriaFilterRequest request);
	Response<PublicacionResponse> getById(@Valid GetByIdRequest request);
	Response<?> create(@Valid CreatePublicacionRequest request);
	Response<?> update(@Valid UpdatePublicacionRequest request);
	Response<?> delete(@Valid GetByIdRequest request);
	
	
	Response<PublicacionDropdownResponse> getDataDropdown();
}

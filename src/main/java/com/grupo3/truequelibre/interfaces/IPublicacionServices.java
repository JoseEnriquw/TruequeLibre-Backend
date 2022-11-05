package com.grupo3.truequelibre.interfaces;

import java.util.List;
import javax.validation.Valid;
import com.grupo3.truequelibre.entity.Publicacion;
import com.grupo3.truequelibre.services.PublicacionService.CreatePublicacionRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetAllByCategoriaRequest;
import com.grupo3.truequelibre.services.PublicacionService.GetByIdRequest;
import com.grupo3.truequelibre.services.PublicacionService.UpdatePublicacionRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IPublicacionServices {
	Response<List<Publicacion>> getAll();
	Response<List<Publicacion>> getAllByCategoria(@Valid GetAllByCategoriaRequest request);
	Response<Publicacion> getById(@Valid GetByIdRequest request);
	Response<?> create(@Valid CreatePublicacionRequest request);
	Response<?> update(@Valid UpdatePublicacionRequest request);
	Response<?> delete(@Valid GetByIdRequest request);
	
}

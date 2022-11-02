package com.grupo3.truequelibre.interfaces;

import java.util.List;
import javax.validation.Valid;
import com.grupo3.truequelibre.entity.Publicacion;
import com.grupo3.truequelibre.services.PublicacionService.CreatePublicacionRequest;
import com.grupo3.truequelibre.services.PublicacionService.UpdatePublicacionRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IPublicacionServices {
	Response<List<Publicacion>> getAll();
	Response<Publicacion> getById(Integer id);
	Response create(@Valid CreatePublicacionRequest request);
	Response update(@Valid UpdatePublicacionRequest request);
	Response delete(Integer id);
	
}

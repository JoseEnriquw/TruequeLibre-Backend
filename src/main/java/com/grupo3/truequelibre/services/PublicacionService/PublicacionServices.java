package com.grupo3.truequelibre.services.PublicacionService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.grupo3.truequelibre.dao.IPublicacionDao;
import com.grupo3.truequelibre.entity.Publicacion;
import com.grupo3.truequelibre.interfaces.IPublicacionServices;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Response;

@Service
public class PublicacionServices implements IPublicacionServices {

	@Autowired
	IPublicacionDao publicacionDao;

	@Override
	public Response<List<Publicacion>> getAll() {
		return new Response<List<Publicacion>>( (List<Publicacion>)publicacionDao.findAll(),HttpStatus.OK);
	}

	@Override
	public Response<Publicacion> getById(Integer id) {
		Optional<Publicacion>entity=publicacionDao.findById(id);
		 Response<Publicacion> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,id,"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
		   response.setBody(entity.get());
		   response.setStatus(HttpStatus.OK);
		}
		return response;
	}
	
}

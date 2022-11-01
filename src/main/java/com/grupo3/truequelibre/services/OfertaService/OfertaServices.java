package com.grupo3.truequelibre.services.OfertaService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.grupo3.truequelibre.dao.IOfertasDao;
import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.interfaces.IOfertaServices;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Response;

@Service
public class OfertaServices implements IOfertaServices {
	
	@Autowired
	IOfertasDao ofertaDao;

	@Override
	public Response<List<Oferta>> getAll() {
		return new Response<List<Oferta>>( (List<Oferta>)ofertaDao.findAll(),HttpStatus.OK);
	}

	@Override
	public Response<Oferta> getById(Integer id) {
		Optional<Oferta>entity=ofertaDao.findById(id);
		 Response<Oferta> response= new Response<>();
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

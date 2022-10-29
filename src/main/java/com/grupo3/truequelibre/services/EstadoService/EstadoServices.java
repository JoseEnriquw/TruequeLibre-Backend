package com.grupo3.truequelibre.services.EstadoService;

import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Response;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.grupo3.truequelibre.dao.IEstadoDao;
import com.grupo3.truequelibre.entity.*;
import com.grupo3.truequelibre.interfaces.IEstadoServices;

@Service
public class EstadoServices implements IEstadoServices {
    
	@Autowired
	private IEstadoDao estadoDao;
	
	public Response<List<Estado>> getAll() {
		
		return new Response<List<Estado>>( (List<Estado>)estadoDao.findAll(),HttpStatus.OK);
	}

	public Response<Estado> getById(Integer id){
		
		 Optional<Estado>entity=estadoDao.findById(id);
		 Response<Estado> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,id,"Estado"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
		   response.setBody(entity.get());
		   response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	public Response<Estado> create(CreateEstado request) {
		Estado estado=new Estado(request.descripcion());
		estado=estadoDao.save(estado);

		return new Response<Estado>(estado, HttpStatus.CREATED);
	}

	public Response delete(Integer id) {
	   estadoDao.deleteById(id);
	   return new Response<Estado>(HttpStatus.NO_CONTENT);	
	}

	public Response update(UpdateEstado request) {

		 Optional<Estado>entity=estadoDao.findById(request.id());
		 Response<Estado> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Estado"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			Estado estado=entity.get();
			estado.setDescripcion(request.descripcion());

			estado=estadoDao.save(estado);
			
		    response.setStatus(HttpStatus.OK);
		}
		return response;
	}

}

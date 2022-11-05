package com.grupo3.truequelibre.services.OfertaService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.grupo3.truequelibre.dao.IEstadoDao;
import com.grupo3.truequelibre.dao.IOfertasDao;
import com.grupo3.truequelibre.dao.IPublicacionDao;
import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.entity.Publicacion;
import com.grupo3.truequelibre.entity.PublicacionesOfertasID;
import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.interfaces.IOfertaServices;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Response;

@Service
@Validated
public class OfertaServices implements IOfertaServices {
	@Autowired
	IOfertasDao ofertaDao;
	@Autowired 
	IPublicacionDao publicacionDao;
	@Autowired
	IEstadoDao estadoDao;

	@Override
	public Response<List<Oferta>> getAll() {
		return new Response<List<Oferta>>( (List<Oferta>)ofertaDao.findByEstadoIdNot(Estados.Inactivo.ordinal()+1),HttpStatus.OK);
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

	@Override
	public Response<?> create(CreateOfertaRequest request) {
		Response<Oferta> response= new Response<>();
		Optional<Publicacion> publicacionPrincipal = publicacionDao.findById(request.id_publicacion_principal());
		if (publicacionPrincipal.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id_publicacion_ofertante(),"Oferta"));
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			
			Optional<Publicacion> publicacionOfertante = publicacionDao.findById(request.id_publicacion_ofertante());
			if(publicacionOfertante.isEmpty()) {
				response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id_publicacion_ofertante(),"Oferta"));
				response.setStatus(HttpStatus.NOT_FOUND);
			}
			else {
				Oferta oferta=new Oferta(new PublicacionesOfertasID(request.id_publicacion_principal(),request.id_publicacion_ofertante()),new Estado(3,"Ofertado"));
				oferta.setPublicacionPrincipal(publicacionPrincipal.get());
				oferta.setPublicacionOferante(publicacionOfertante.get());
				ofertaDao.save(oferta);
				response.setStatus(HttpStatus.CREATED);
			}
		}
		return response;
	}

	@Override
	public Response<?> update(UpdateOfertaRequest request) {
		Optional<Oferta>entity=ofertaDao.findById(request.id());
		 Response<Oferta> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			Optional<Estado> estado = estadoDao.findById(request.id_estado());
			if(estado.isEmpty()) {
				response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id_estado(),"Estado"));		  
				response.setStatus(HttpStatus.NOT_FOUND);
			}
			else {
				entity.get().setEstado(estado.get());
				ofertaDao.save(entity.get());
				response.setStatus(HttpStatus.OK);
			}
		}
		return response;
	}

	@Override
	public Response<?> delete(Integer id) {
		Optional<Oferta>entity=ofertaDao.findById(id);
		Response<Oferta> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,id,"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			Oferta oferta = entity.get();
			Optional<Estado> estado= estadoDao.findById(Estados.Inactivo.ordinal()+1); 
			oferta.setEstado(estado.get());
			ofertaDao.save(oferta);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}
	

	@Override
	public Response<List<Oferta>> filtrar(FiltrarOfertaRequest request) {
		Optional<List<Oferta>>listOfertas=null;
		Optional<Estado> estado= estadoDao.findById(request.id_estado()); 
		switch(request.estado()) {
			case "Recibidos":listOfertas=ofertaDao.findByEstadoAndPublicacionPrincipal_Usuario(estado.get(),new Usuario(request.id_usuario()));
			break;
			case "Enviados":listOfertas=ofertaDao.findByEstadoAndPublicacionOferante_Usuario(estado.get(),new Usuario(request.id_usuario()));
			break;
			case "Aceptados":listOfertas=ofertaDao.findByEstadoAndPublicacionOferante_UsuarioOrPublicacionPrincipal_Usuario(estado.get(),new Usuario(request.id_usuario()),new Usuario(request.id_usuario()));
			break;
		}
		Response<List<Oferta>> response= new Response<>();
		if (listOfertas == null || listOfertas.isEmpty()) {
			response.AddError("#1", "","No ofertas were found with this filters");		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			response.setBody(listOfertas.get());
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	

	

}

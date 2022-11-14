package com.grupo3.truequelibre.services.OfertaService;

import java.util.ArrayList;
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
import com.grupo3.truequelibre.responses.Oferta.FinalizarTruequeResponse;
import com.grupo3.truequelibre.responses.Oferta.OfertaResponse;
import com.grupo3.truequelibre.responses.Oferta.OfertaResponseNotificacion;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Response;
import com.grupo3.truequelibre.tools.StringUtils;

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
	public Response<OfertaResponseNotificacion> getById(Integer id) {
		Optional<Oferta>entity=ofertaDao.findById(id);
		 Response<OfertaResponseNotificacion> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,id,"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			Integer id_usuario_principal = entity.get().getPublicacionPrincipal().getUsuario().getId();
			String nombre_usuario_principal = StringUtils.armarNombre(entity.get().getPublicacionPrincipal().getUsuario());
			byte[] imagen_usuario_principal = entity.get().getPublicacionPrincipal().getUsuario().getPersona().getImagenes();
			Integer id_usuario_ofertante = entity.get().getPublicacionOferante().getUsuario().getId();
			String nombre_usuario_ofertante = StringUtils.armarNombre(entity.get().getPublicacionOferante().getUsuario());
			byte[] imagen_usuario_ofertante = entity.get().getPublicacionOferante().getUsuario().getPersona().getImagenes();
			OfertaResponseNotificacion rsp = new OfertaResponseNotificacion(
					id_usuario_principal, nombre_usuario_principal,imagen_usuario_principal,id_usuario_ofertante,nombre_usuario_ofertante,imagen_usuario_ofertante
					);
		   response.setBody(rsp);
		   
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
				Optional<Estado> estadoPublicacion = estadoDao.findById(Estados.Inactivo.ordinal()+1);
				Optional<Estado> estadoRechazado = estadoDao.findById(Estados.Rechazado.ordinal()+1);
				Oferta result=entity.get();
				result.setEstado(estado.get());
				result.getPublicacionOferante().setEstado(estadoPublicacion.get());
			    result.getPublicacionPrincipal().setEstado(estadoPublicacion.get());
			    
			    //Integer idOferta, Integer idPublicacionPrincipal, Integer idPublicacionOfertante
			    Optional<List<Oferta>> ofertasARechazar=ofertaDao.findByAll(result.getId(), result.getPublicacionPrincipal().getId(), result.getPublicacionOferante().getId());
			    
			    for(Oferta item : ofertasARechazar.get()) 
			    {
			    	item.setEstado(estadoRechazado.get());
			    }
			    
			    ofertaDao.saveAll(ofertasARechazar.get());
				ofertaDao.save(result);
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
	public Response<List<OfertaResponse>> filtrar(FiltrarOfertaRequest request) {
		Optional<List<Oferta>>listOfertas=null;
		 
		switch(request.estado()) {
			case "Recibidos":listOfertas=ofertaDao.findByEstadoIdAndPublicacionPrincipal_UsuarioId(Estados.Ofertado.ordinal()+1,request.id_usuario());
			break;
			case "Enviados":listOfertas=ofertaDao.findByEstadoIdAndPublicacionOferante_Usuario(Estados.Ofertado.ordinal()+1,new Usuario(request.id_usuario()));
			break;
			case "Aceptados":listOfertas=ofertaDao.findByAllOf(Estados.Aceptado.ordinal()+1,Estados.Propiestafinalizado1.ordinal()+1,request.id_usuario());
			break;
		}
		Response<List<OfertaResponse>> response= new Response<>();
		if (listOfertas == null || listOfertas.isEmpty()) {
			response.AddError("#1", "","No ofertas were found with this filters");		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {

			List<OfertaResponse> content= new ArrayList<>();
		
			
			for(Oferta item: listOfertas.get())
			{				
				content.add(new OfertaResponse(
			    		   item.getPublicacionOferante().getNombre(),
			    		  item.getPublicacionOferante().getDescripcion(),
			    		  item.getPublicacionOferante().getImagenes(),
			    		  item.getPublicacionPrincipal().getNombre(),
			    		  item.getPublicacionPrincipal().getDescripcion(),
			    		  item.getPublicacionPrincipal().getImagenes(),			    		  
			    		  item.getEstado().getId(),
			    		  item.getPublicacionPrincipal().getUsuario().getId(),
			    		  item.isUsuario_principal_acepto(),
			    		  item.isUsuario_ofertante_acepto(),
			    		  item.getId() 
			    		  ));
			}
			response.setBody(content);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	@Override
	public Response<FinalizarTruequeResponse> GetByIdEstadoOferta(Integer id) {
		Optional<Oferta>entity=ofertaDao.findById(id);
		 Response<FinalizarTruequeResponse> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,id,"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {

			Oferta result=entity.get();
			FinalizarTruequeResponse content= new FinalizarTruequeResponse(
					result.getId(),
					result.isUsuario_principal_acepto(),
					result.isUsuario_ofertante_acepto());
			
			response.setBody(content);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	@Override
	public Response<?> updateFinalizarTrueque(UpdateFinalizarRequest request) {
		
		Optional<Oferta>entity=ofertaDao.findById(request.id());
		 Response<FinalizarTruequeResponse> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
				Oferta result=entity.get();
				result.setUsuario_principal_acepto(request.usuario_principal_acepto());
				result.setUsuario_ofertante_acepto(request.usuario_ofertante_acepto());	   
			
				ofertaDao.save(result);
				response.setStatus(HttpStatus.OK);
			}
		
		return response;
	}
	

}

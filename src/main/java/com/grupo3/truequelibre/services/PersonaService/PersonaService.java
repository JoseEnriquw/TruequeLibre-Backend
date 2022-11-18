package com.grupo3.truequelibre.services.PersonaService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.grupo3.truequelibre.dao.IOfertasDao;
import com.grupo3.truequelibre.dao.IPersonaDao;
import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.Localidad;
import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.entity.Persona;
import com.grupo3.truequelibre.entity.Publicacion;
import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.interfaces.IOfertaServices;
import com.grupo3.truequelibre.interfaces.IPersonaServices;
import com.grupo3.truequelibre.responses.Oferta.OfertaResponseNotificacion;
import com.grupo3.truequelibre.responses.Persona.PersonaResponse;
import com.grupo3.truequelibre.services.OfertaService.UpdateOfertaRequest;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Response;
import com.grupo3.truequelibre.tools.StringUtils;

@Service
@Validated
public class PersonaService implements IPersonaServices{
	@Autowired
	IPersonaDao personaDao;
	
	
	
	@Override
	public Response<PersonaResponse> getById(Integer id) {
		Optional<Persona>entity=personaDao.findById(id);	
		Response<PersonaResponse> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,id,"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			
			 String dni= entity.get().getDni();	
			 String nombre =entity.get().getNombre();
			 String apellido=entity.get().getApellido();
			 String direccion=entity.get().getDireccion();
			 Date fechaNacimiento=entity.get().getFechaNacimiento();	
			 String telefono=entity.get().getTelefono();	
			 Localidad localidad=entity.get().getLocalidad();	  
		   	 byte[] imagenes=entity.get().getImagenes();
			 
		   	PersonaResponse rsp = new PersonaResponse(
		   			dni,nombre,apellido,direccion,fechaNacimiento,telefono,localidad,imagenes);
		   response.setBody(rsp);		   
		   response.setStatus(HttpStatus.OK);
		}
		return response;
	}
	
	@Override
	public Response<?> updateImg(UpdatePersonaRequest request) {
		Optional<Persona>pers=personaDao.findById(request.idusuario());
		
		 Response<Persona> response= new Response<>();
		if(pers.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.idusuario(),"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
			else {
		       Persona entity=pers.get();
			   entity.setImagenes(request.imagenes());			    
			  
			   personaDao.save(entity);
			   response.setStatus(HttpStatus.OK);
			}
		
		return response;
	}
}

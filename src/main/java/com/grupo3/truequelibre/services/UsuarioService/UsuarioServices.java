package com.grupo3.truequelibre.services.UsuarioService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.grupo3.truequelibre.dao.IEstadoDao;
import com.grupo3.truequelibre.dao.ILocalidadDao;
import com.grupo3.truequelibre.dao.IUsuarioDao;
import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.Localidad;
import com.grupo3.truequelibre.entity.Persona;
import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.interfaces.IUsuarioServices;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Response;

@Service
@Validated
public class UsuarioServices implements IUsuarioServices{

	@Autowired
	IUsuarioDao usuarioDao;
	
	@Autowired
	ILocalidadDao localidadDao;
	
	@Autowired 
	IEstadoDao estadoDao;
	
	@Override
	public Response<List<Usuario>> getAll() {
		return new Response<List<Usuario>>(usuarioDao.findByEstadoIdNot(Estados.Inactivo.ordinal()+1),HttpStatus.OK);
	}
	
	@Override
	public Response<Usuario> getByEmail(String email) {
		Optional<Usuario> usuario = usuarioDao.findByMailAndEstadoIdNot(email, 2);
		Response<Usuario> response = new Response<>();
		if (usuario.isEmpty()) {
			response.AddError("#1", "email","The Email " + email + " of usuario was not found in the database");
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			response.setBody(usuario.get());
			response.setStatus(HttpStatus.OK);
		}
		return response;		
	}

	@Override
	public Response<Usuario> create(@Valid CreateUsuarioRequest request) {
		Response<Usuario> response = new Response<>();
		
		Optional<Localidad> ubicacion =localidadDao.findById(request.localidad());
		if(ubicacion.isEmpty()) {
			response.AddError("#1", "id Localidad", String.format(ErrorMessage.NOTFOUND,request.localidad(),"Localidad"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			Optional<Estado> estado= estadoDao.findById(Estados.Activo.ordinal()+1); 
			Persona persona = new Persona(request.dni(),request.nombre(),request.apellido(),request.direccion(),request.fechaNacimiento(),request.telefono(),ubicacion.get());
			Usuario usuario = new Usuario(request.mail(),request.contrasenia(),estado.get(),persona);
			usuario = usuarioDao.save(usuario);
			response.setBody(usuario);
			response.setStatus(HttpStatus.OK);
		}
		
		
		
		return response;
	}

	@Override
	public Response<Usuario> update(@Valid UpdateUsuarioRequest request) {
		Response<Usuario> response = new Response<>();
		Optional<Usuario> entity = usuarioDao.findByMailAndEstadoIdNot(request.mail(),Estados.Inactivo.ordinal()+1);
		if(entity.isEmpty()) {
			response.AddError("#1", "email","The Email " + request.mail() + " of usuario was not found in the database");
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			Optional<Localidad> ubicacion =localidadDao.findById(request.localidad());
			if(ubicacion.isEmpty()) {
				response.AddError("#1", "id Localidad", String.format(ErrorMessage.NOTFOUND,request.localidad(),"Localidad"));		  
				response.setStatus(HttpStatus.NOT_FOUND);
			}
			else {
				Usuario usuario = entity.get();
				usuario.getPersona().setNombre(request.nombre());
				usuario.getPersona().setApellido(request.apellido());
				usuario.getPersona().setDireccion(request.direccion());
				usuario.getPersona().setFechaNacimiento(request.fechaNacimiento());
				usuario.getPersona().setLocalidad(ubicacion.get());
				usuario.getPersona().setTelefono(request.telefono());
				usuario.setContrasenia(request.contrasenia());
				usuario = usuarioDao.save(usuario);
				response.setBody(usuario);
				response.setStatus(HttpStatus.OK);
			}			
		}
		
		return response;
		
	}

	@Override
	public Response<?> delete(String email) {
		Response<Usuario> response = new Response<>();
		Optional<Usuario> entity = usuarioDao.findByMailAndEstadoIdNot(email,Estados.Inactivo.ordinal()+1);
		if(entity.isEmpty()) {
			response.AddError("#1", "email","The Email " + email + " of usuario was not found in the database");
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			Usuario usuario = entity.get();
			Optional<Estado> estado= estadoDao.findById(Estados.Inactivo.ordinal()+1); 
			usuario.setEstado(estado.get());
			usuarioDao.save(usuario);
			response.setBody(usuario);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

}

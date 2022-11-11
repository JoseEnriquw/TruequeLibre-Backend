package com.grupo3.truequelibre.services.UsuarioService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.grupo3.truequelibre.dao.IEstadoDao;
import com.grupo3.truequelibre.dao.ILocalidadDao;
import com.grupo3.truequelibre.dao.ISeguridadUsuarioDao;
import com.grupo3.truequelibre.dao.IUsuarioDao;
import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.Localidad;
import com.grupo3.truequelibre.entity.Persona;
import com.grupo3.truequelibre.entity.SeguridadUsuario;
import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.interfaces.IUsuarioServices;
import com.grupo3.truequelibre.responses.Localidad.LocalidadResponse;
import com.grupo3.truequelibre.responses.Usuario.UsuarioDropdownResponse;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Mailer;
import com.grupo3.truequelibre.tools.Response;
import com.grupo3.truequelibre.tools.SeguridadTools;
import com.grupo3.truequelibre.tools.StringUtils;

@Service
@Validated
public class UsuarioServices implements IUsuarioServices{

	@Autowired
	IUsuarioDao usuarioDao;
	
	@Autowired
	ISeguridadUsuarioDao seguridadUsuarioDao;
	
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
	public Response<?> create(CreateUsuarioRequest request) {
		Response<?> response = new Response<>();
		
		Optional<Localidad> ubicacion =localidadDao.findById(request.localidad());
		if(ubicacion.isEmpty()) {
			response.AddError("#1", "id Localidad", String.format(ErrorMessage.NOTFOUND,request.localidad(),"Localidad"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			 Optional<Estado> estado= estadoDao.findById(Estados.Activo.ordinal()+1); 
			 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		     String fecha = String.valueOf(request.fechaNacimiento());
		     Date aux = null;
		     try {
				aux = (Date) formato.parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Persona persona = new Persona(request.dni(),request.nombre(),request.apellido(),request.direccion(),request.fechaNacimiento(),request.telefono(),ubicacion.get());
			Usuario usuario = new Usuario(request.mail(),request.contrasenia(),estado.get(),persona);
			usuario = usuarioDao.save(usuario);		
			response.setStatus(HttpStatus.CREATED);
		}
		
		
		
		return response;
	}

	@Override
	public Response<Usuario> update(UpdateUsuarioRequest request) {
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

	@Override
	public Response<?> login(LoginUsuarioRequest request) {
		Response<Usuario> response = new Response<>();
		Optional<Usuario> entity = usuarioDao.findByMailAndEstadoIdNot(request.email(),Estados.Inactivo.ordinal()+1);
		if(entity.isEmpty()) {
			response.AddError("#1", "email","The Email " + request.email() + " of usuario was not found in the database");
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			Usuario usuario = entity.get();
			if(usuario.getContrasenia().equals(request.contrasenia())) {
				response.setBody(usuario);
				response.setStatus(HttpStatus.OK);
			}
			else {
				response.AddError("#2", "password","The password is invalid");
				response.setStatus(HttpStatus.NOT_FOUND);
			}
		}
		return response;
	}

	@Override
	public Response<?> recuperar(RecuperarUsuarioRequest request) {
		Response<SeguridadUsuario> response = new Response<>();
		Optional<Usuario> entity = usuarioDao.findByMailAndEstadoIdNot(request.email(),Estados.Inactivo.ordinal()+1);
		if(entity.isEmpty()) {
			response.AddError("#1", "email","The Email " + request.email() + " of usuario was not found in the database");
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			Usuario usuario = entity.get();
			SeguridadUsuario seg = SeguridadTools.generarSeguridadUsuario(usuario);
			Optional<Estado> estado= estadoDao.findById(Estados.Activo.ordinal()+1); 
			seg.setEstado(estado.get());
			seg = seguridadUsuarioDao.save(seg);
			if (Mailer.EnviarMail(seg.getToken())) {
				response.setBody(seg);
				response.setStatus(HttpStatus.OK);	
			}
		}
		return response;
	}

	@Override
	public Response<?> recuperarVerificar(RecuperarVerificarUsuarioRequest request) {
		Response<Usuario> response = new Response<>();
		Optional<SeguridadUsuario> entity = seguridadUsuarioDao.findByTokenAndEstadoIdNot(request.token(),Estados.Inactivo.ordinal()+1);
		if(entity.isEmpty()) {
			response.AddError("#1", "Token","The Token " + request.token() + " was not found in the database");
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			SeguridadUsuario seguridad = entity.get();
			if (seguridad.isExpired()) {
				response.AddError("#2", "Token","The Token " + request.token() + " is expired");
				response.setStatus(HttpStatus.UNAUTHORIZED);
			}
			else {
				if(seguridad.getToken().equals(request.token())) {
					// Inability token
					Optional<Estado> estado= estadoDao.findById(Estados.Inactivo.ordinal()+1); 
					seguridad.setEstado(estado.get());
					seguridadUsuarioDao.save(seguridad);
					
					// Change password
					Usuario usuario = seguridad.getUsuario();
					usuario.setContrasenia(request.newPassword());
					usuario = usuarioDao.save(usuario);
					response.setBody(usuario);
					response.setStatus(HttpStatus.OK);
					
				}
				else {
					response.AddError("#3", "Token","The Token " + request.token() + " is invalid");
				}
				
			}
		}
		return response;
	}
	
	@Override
	public Response<UsuarioDropdownResponse> getDataDropdown() {
		Response<UsuarioDropdownResponse> response = new Response<>();	
		List<Localidad> localidades = localidadDao.findAll();			
		List<LocalidadResponse> listaLocalidadResponse = new ArrayList<>();	
	
		for(Localidad item: localidades) {
			listaLocalidadResponse.add(new LocalidadResponse(item.getId(),StringUtils.armarUbicacion(item)));
		}
		UsuarioDropdownResponse listaDropdown = new UsuarioDropdownResponse(listaLocalidadResponse);
		
		response.setBody(listaDropdown);
		response.setStatus(HttpStatus.OK);
		
		return response;
		
	}

}

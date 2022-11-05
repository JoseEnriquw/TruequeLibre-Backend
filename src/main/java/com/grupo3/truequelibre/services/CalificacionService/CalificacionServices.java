package com.grupo3.truequelibre.services.CalificacionService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.grupo3.truequelibre.dao.ICalificacionDao;
import com.grupo3.truequelibre.dao.IUsuarioDao;
import com.grupo3.truequelibre.entity.CalificacionUsuarios;
import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.interfaces.ICalificacionServices;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Fecha;
import com.grupo3.truequelibre.tools.Response;

@Service
@Validated
public class CalificacionServices implements ICalificacionServices{

	@Autowired
	ICalificacionDao calificacionDao;
	
	@Autowired
	IUsuarioDao usuarioDao;
	
	
	@Override
	public Response<List<CalificacionUsuarios>> getAll() {
		List<CalificacionUsuarios> listaCalificaciones = calificacionDao.findAll();
		return new Response<>(listaCalificaciones,HttpStatus.OK);		
	}


	@Override
	public Response<CalificacionUsuarios> create(@Valid CreateCalificacionRequest request) {
		Response<CalificacionUsuarios> response = new Response<>();
		 Optional<Usuario>usuario =usuarioDao.findById(request.idUsuarioCalificado());
		 if (usuario.isEmpty()) {
			 response.AddError("#1", "idUsuario", String.format(ErrorMessage.NOTFOUND,request.idUsuarioCalificado(),"Usuario"));		  
			 response.setStatus(HttpStatus.NOT_FOUND);
		 }
		 else {
			 Optional<Usuario>usuarioCalificador =usuarioDao.findById(request.idUsuarioCalificador());
			 if (usuarioCalificador.isEmpty()) {
				 response.AddError("#1", "idUsuario", String.format(ErrorMessage.NOTFOUND,request.idUsuarioCalificado(),"Usuario"));		  
				 response.setStatus(HttpStatus.NOT_FOUND);
			 }
			 else {
				 CalificacionUsuarios calificacion = new CalificacionUsuarios(request.estrellas(),request.comentario(),Fecha.getFechaHoy(),usuario.get(),usuarioCalificador.get());
				 calificacion = calificacionDao.save(calificacion);
				 response.setBody(calificacion);
				 response.setStatus(HttpStatus.OK);
			 }
			 
		 }
		return response;
	}


	@Override
	public Response<List<CalificacionUsuarios>> getByTipo(@Valid FiltroCalificacionRequest request) {
		Response<List<CalificacionUsuarios>> response = new Response<>();
		 Optional<Usuario>usuario =usuarioDao.findById(request.idUsuario());
		 if (usuario.isEmpty()) {
			 response.AddError("#1", "idUsuario", String.format(ErrorMessage.NOTFOUND,request.idUsuario(),"Usuario"));		  
			 response.setStatus(HttpStatus.NOT_FOUND);
		 }
		 else {
			 Optional<List<CalificacionUsuarios>> listaCalificaciones=null;
			switch(request.tipoCalificacion()) {
				case "Buena":
				 listaCalificaciones= calificacionDao.findByEstrellasGreaterThanEqual((short)3);
				break;
				case "Mala":
				 listaCalificaciones = calificacionDao.findByEstrellasLessThanEqual((short)2);
				break;
			}
			if (listaCalificaciones == null || listaCalificaciones.isEmpty()) {
				response.AddError("#1", "calificaciones","No calificaciones were found with this filters");		  
				 response.setStatus(HttpStatus.NOT_FOUND);
			}
			else {
				response.setBody(listaCalificaciones.get());
				response.setStatus(HttpStatus.OK);
			}
		 }
		 
		 return response;
		 
	}
}



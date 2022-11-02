package com.grupo3.truequelibre.services.PublicacionService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.grupo3.truequelibre.dao.ICategoriaDao;
import com.grupo3.truequelibre.dao.ICondicionDao;
import com.grupo3.truequelibre.dao.IEstadoDao;
import com.grupo3.truequelibre.dao.ILocalidadDao;
import com.grupo3.truequelibre.dao.IPublicacionDao;
import com.grupo3.truequelibre.dao.IUsuarioDao;
import com.grupo3.truequelibre.entity.Categoria;
import com.grupo3.truequelibre.entity.Condicion;
import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.Localidad;
import com.grupo3.truequelibre.entity.Publicacion;
import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.interfaces.IPublicacionServices;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Response;

@Service
@Validated
public class PublicacionServices implements IPublicacionServices {

	@Autowired
	IPublicacionDao publicacionDao;
	@Autowired
	IUsuarioDao usuarioDao;
	@Autowired
	ICategoriaDao categoriaDao;
	@Autowired
	ICondicionDao condicionDao;
	@Autowired
	ILocalidadDao localidadDao;
	@Autowired
	IEstadoDao estadoDao;
	

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

	@Override
	public Response create( CreatePublicacionRequest request) {
		 Response<?> response= new Response<>();	
		 
		 ////Valida id USUARIO
		 Optional<Usuario>usuario =usuarioDao.findById(request.idUsuario());
		if(usuario.isEmpty()) {
			response.AddError("#1", "idUsuario", String.format(ErrorMessage.NOTFOUND,request.idUsuario(),"Usuario"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			
		       ////Valida id CATEGORIA
			   Optional<Categoria>categoria =categoriaDao.findById(request.idCategoria());
				if(categoria.isEmpty()) {
					response.AddError("#1", "idCategoria", String.format(ErrorMessage.NOTFOUND,request.idCategoria(),"Categoria"));		  
					response.setStatus(HttpStatus.NOT_FOUND);
				}else {

					////Valida id CATEGORIAPRETENDIDA
					Optional<Categoria>categoriaPretendida =categoriaDao.findById(request.idCategoriaPretendida());
					if(categoriaPretendida.isEmpty()) {
						response.AddError("#1", "idCategoriaPretendida", String.format(ErrorMessage.NOTFOUND,request.idCategoriaPretendida(),"Categoria"));		  
						response.setStatus(HttpStatus.NOT_FOUND);
					}else {
						
						////Valida id CONDICION
						Optional<Condicion> condicion =condicionDao.findById(request.idCondicion());
						if(condicion.isEmpty()) {
							response.AddError("#1", "idCondicion", String.format(ErrorMessage.NOTFOUND,request.idCondicion(),"Condicion"));		  
							response.setStatus(HttpStatus.NOT_FOUND);
						}else {
							
							////Valida id UBICACION
							Optional<Localidad> ubicacion =localidadDao.findById(request.idUbicacion());
							if(ubicacion.isEmpty()) {
								response.AddError("#1", "idUbicacion", String.format(ErrorMessage.NOTFOUND,request.idUbicacion(),"Localidad"));		  
								response.setStatus(HttpStatus.NOT_FOUND);
							}else {

							    ////Valida id UBICACIONPRETENDIDA
								Optional<Localidad> ubicacionPretendida =localidadDao.findById(request.idUbicacionPretendida());
								if(ubicacionPretendida.isEmpty()) {
									response.AddError("#1", "idUbicacionPretendida", String.format(ErrorMessage.NOTFOUND,request.idUbicacionPretendida(),"Localidad"));		  
									response.setStatus(HttpStatus.NOT_FOUND);
								}else {

									Optional<Estado> estado= estadoDao.findById(Estados.EnRevision.ordinal()+1); 
									Publicacion publicacion=new Publicacion(usuario.get(),request.nombre(),request.descripcion(),categoria.get(),categoriaPretendida.get(),
											request.imagenes(),condicion.get(),ubicacion.get(),ubicacionPretendida.get(),estado.get());
									
									publicacionDao.save(publicacion);
									response.setStatus(HttpStatus.CREATED);
								}
							}
						}
					}
				}
		}
		
		return response;
	}

	@Override
	public Response update(UpdatePublicacionRequest request) {
 
        Response<?> response= new Response<>();	
		 
		 ////Valida id PUBLICACION
		 Optional<Publicacion> publicacion =publicacionDao.findById(request.id());
		if(publicacion.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Publicacion"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			
		       ////Valida id CATEGORIA
			   Optional<Categoria>categoria =categoriaDao.findById(request.idCategoria());
				if(categoria.isEmpty()) {
					response.AddError("#1", "idCategoria", String.format(ErrorMessage.NOTFOUND,request.idCategoria(),"Categoria"));		  
					response.setStatus(HttpStatus.NOT_FOUND);
				}else {

					////Valida id CATEGORIAPRETENDIDA
					Optional<Categoria>categoriaPretendida =categoriaDao.findById(request.idCategoriaPretendida());
					if(categoriaPretendida.isEmpty()) {
						response.AddError("#1", "idCategoriaPretendida", String.format(ErrorMessage.NOTFOUND,request.idCategoriaPretendida(),"Categoria"));		  
						response.setStatus(HttpStatus.NOT_FOUND);
					}else {
						
						////Valida id CONDICION
						Optional<Condicion> condicion =condicionDao.findById(request.idCondicion());
						if(condicion.isEmpty()) {
							response.AddError("#1", "idCondicion", String.format(ErrorMessage.NOTFOUND,request.idCondicion(),"Condicion"));		  
							response.setStatus(HttpStatus.NOT_FOUND);
						}else {
							
							////Valida id UBICACION
							Optional<Localidad> ubicacion =localidadDao.findById(request.idUbicacion());
							if(ubicacion.isEmpty()) {
								response.AddError("#1", "idUbicacion", String.format(ErrorMessage.NOTFOUND,request.idUbicacion(),"Localidad"));		  
								response.setStatus(HttpStatus.NOT_FOUND);
							}else {

							    ////Valida id UBICACIONPRETENDIDA
								Optional<Localidad> ubicacionPretendida =localidadDao.findById(request.idUbicacionPretendida());
								if(ubicacionPretendida.isEmpty()) {
									response.AddError("#1", "idUbicacionPretendida", String.format(ErrorMessage.NOTFOUND,request.idUbicacionPretendida(),"Localidad"));		  
									response.setStatus(HttpStatus.NOT_FOUND);
								}else {

									 ////Valida id ESTADO
									Optional<Estado> estado = estadoDao.findById(request.idEstado());
									if(estado.isEmpty()) {
										response.AddError("#1", "idEstado", String.format(ErrorMessage.NOTFOUND,request.idEstado(),"Estado"));		  
										response.setStatus(HttpStatus.NOT_FOUND);
									}else {
									    Publicacion entity=publicacion.get();
									    entity.setNombre(request.nombre());
									    entity.setDescripcion(request.descripcion());
									    entity.setCategoria(categoria.get());
									    entity.setCategoriaPretendida(categoriaPretendida.get());
                                        entity.setCondicion(condicion.get());
                                        entity.setUbicacion(ubicacion.get());
                                        entity.setUbicacionPretendida(ubicacionPretendida.get());
                                        entity.setEstado(estado.get());
                                        entity.setImagenes(request.imagenes());
									    
										publicacionDao.save(entity);
										response.setStatus(HttpStatus.OK);
									}
								}
							}
						}
					}
				}
		}
		
		return response;
	}

	
}

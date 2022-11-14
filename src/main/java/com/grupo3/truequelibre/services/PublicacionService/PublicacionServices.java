package com.grupo3.truequelibre.services.PublicacionService;

import java.util.ArrayList;
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
import com.grupo3.truequelibre.responses.Categoria.CategoriaResponse;
import com.grupo3.truequelibre.responses.Condicion.CondicionResponse;
import com.grupo3.truequelibre.responses.Localidad.LocalidadResponse;
import com.grupo3.truequelibre.responses.Publicacion.PublicacionDropdownResponse;
import com.grupo3.truequelibre.responses.Publicacion.PublicacionResponse;
import com.grupo3.truequelibre.responses.Usuario.UsuarioPublicacionResponse;
import com.grupo3.truequelibre.tools.ConverterImagenes;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Response;
import com.grupo3.truequelibre.tools.StringUtils;

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
	public Response<List<PublicacionResponse>> getAll(GetAllPublicacionRequest request) {
		List<PublicacionResponse> lista= new ArrayList<>();
		List<Publicacion> result=(List<Publicacion>)publicacionDao.findByUser(request.usuario());
		for(Publicacion item: result) 
		{
			lista.add(new PublicacionResponse(item.getId(), new UsuarioPublicacionResponse(item.getUsuario().getId(),StringUtils.armarNombre(item.getUsuario()),item.getUsuario().getPersona().getImagenes())
					,item.getNombre(),item.getDescripcion(),item.getCategoria().getDescripcion() ,item.getCondicion().getDescripcion(),StringUtils.armarUbicacion(item.getUbicacion()),StringUtils.armarUbicacion(item.getUbicacionPretendida()),
					item.getCategoriaPretendida().getDescripcion(),item.getImagenes()));
		}
		return new Response<List<PublicacionResponse>>( lista,HttpStatus.OK);
	}

	@Override
	public Response<PublicacionResponse> getById(GetByIdRequest request) {
		Optional<Publicacion>entity=publicacionDao.findById(request.id());
		 Response<PublicacionResponse> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Publicacion"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
		   Publicacion obj=entity.get();
		   response.setBody(new PublicacionResponse(obj.getId(), new UsuarioPublicacionResponse(obj.getUsuario().getId(),StringUtils.armarNombre(obj.getUsuario()),obj.getUsuario().getPersona().getImagenes())
					,obj.getNombre(),obj.getDescripcion(),obj.getCategoria().getDescripcion(), obj.getCondicion().getDescripcion(),StringUtils.armarUbicacion(obj.getUbicacion()),StringUtils.armarUbicacion(obj.getUbicacionPretendida()),
					obj.getCategoriaPretendida().getDescripcion(),obj.getImagenes()));
		   response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	@Override
	public Response<?> create( CreatePublicacionRequest request) {
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
	public Response<?> update(UpdatePublicacionRequest request) {
 
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

	@Override
	public Response<?> delete(@Valid GetByIdRequest request) {
		Optional<Publicacion>entity=publicacionDao.findById(request.id());
		 Response<Publicacion> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Publicacion"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
		   Optional<Estado> estado= estadoDao.findById(Estados.Inactivo.ordinal()+1); 
           Publicacion publicacion= entity.get();
           publicacion.setEstado(estado.get());
           
           publicacionDao.save(publicacion);
           response.setStatus(HttpStatus.OK);
		}
		return response;	
		}

	@Override
	public Response<List<PublicacionResponse>> getAllByCategoria(GetAllByCategoriaRequest request) {
		Optional<Categoria> categoria =categoriaDao.findById(request.categoria());
		 Response<List<PublicacionResponse>> response= new Response<>();
		if(categoria.isEmpty()) {
			response.AddError("#1", "idCategoria", String.format(ErrorMessage.NOTFOUND,request.categoria(),"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			List<PublicacionResponse> lista= new ArrayList<>();
			List<Publicacion> result= publicacionDao.findByCategoria(request.categoria(),request.usuario());
			for(Publicacion item: result) 
			{
				lista.add(new PublicacionResponse(item.getId(), new UsuarioPublicacionResponse(item.getUsuario().getId(),StringUtils.armarNombre(item.getUsuario()),item.getUsuario().getPersona().getImagenes())
						,item.getNombre(),item.getDescripcion(),item.getCategoria().getDescripcion(),item.getCondicion().getDescripcion(),StringUtils.armarUbicacion(item.getUbicacion()),StringUtils.armarUbicacion(item.getUbicacionPretendida()),
						item.getCategoriaPretendida().getDescripcion(),item.getImagenes()));
			}
			
			response.setBody(lista);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	@Override
	public Response<List<PublicacionResponse>> getAllByCategoriaFilter(@Valid GetAllByCategoriaFilterRequest request) {
		Optional<Categoria> categoria =categoriaDao.findById(request.categoria());
		 Response<List<PublicacionResponse>> response= new Response<>();
		if(categoria.isEmpty()) {
			response.AddError("#1", "idCategoria", String.format(ErrorMessage.NOTFOUND,request.categoria(),"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			List<PublicacionResponse> lista= new ArrayList<>();
			List<Publicacion> result= publicacionDao.findByCategoriaIdNombreAndUsuarioIdNotIncompleto(request.categoria(),request.search(),request.usuario());
			for(Publicacion item: result) 
			{
				lista.add(new PublicacionResponse(item.getId(), new UsuarioPublicacionResponse(item.getUsuario().getId(),StringUtils.armarNombre(item.getUsuario()),item.getUsuario().getPersona().getImagenes())
						,item.getNombre(),item.getDescripcion(),item.getCategoria().getDescripcion(),item.getCondicion().getDescripcion(),StringUtils.armarUbicacion(item.getUbicacion()),StringUtils.armarUbicacion(item.getUbicacionPretendida()),
						item.getCategoriaPretendida().getDescripcion(),item.getImagenes()));
			}
			response.setBody(lista);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	@Override
	public Response<PublicacionDropdownResponse> getDataDropdown() {
		Response<PublicacionDropdownResponse> response = new Response<>();
		
		List<Categoria> categorias = categoriaDao.findAll();
		List<Condicion> condiciones = condicionDao.findAll();
		List<Localidad> localidades = localidadDao.findAll();
		
		List<CategoriaResponse> listaCategoriaResponse = new ArrayList<>();
		List<CondicionResponse> listaCondicionResponse = new ArrayList<>();
		List<LocalidadResponse> listaLocalidadResponse = new ArrayList<>();
		
		for(Categoria item: categorias) {
			listaCategoriaResponse.add(new CategoriaResponse(item.getId(),item.getDescripcion()));
		}
		for(Condicion item: condiciones) {
			listaCondicionResponse.add(new CondicionResponse(item.getId(),item.getDescripcion()));
		}
		for(Localidad item: localidades) {
			listaLocalidadResponse.add(new LocalidadResponse(item.getId(),StringUtils.armarUbicacion(item)));
		}
		PublicacionDropdownResponse listaDropdown = new PublicacionDropdownResponse(listaCategoriaResponse,listaCondicionResponse,listaLocalidadResponse);
		
		response.setBody(listaDropdown);
		response.setStatus(HttpStatus.OK);
		
		return response;
		
	}

	@Override
	public Response<List<Publicacion>> cargarImagenes() {
		String[] imagenes = {"https://acortar.link/6esHDu","https://acortar.link/vTkrv2","https://acortar.link/Nkecos","https://acortar.link/PIFoRr","https://acortar.link/9ViqC5","https://acortar.link/yYQQTY","https://acortar.link/TH9ZmU","https://acortar.link/4kznRa","https://acortar.link/GuncSW","https://acortar.link/pS9jB1","https://acortar.link/zqZ3b5","https://acortar.link/pcTDns","https://acortar.link/16ALA1","https://acortar.link/HYMsby","https://acortar.link/gi7Lvr","https://acortar.link/ESDJBA","https://acortar.link/eSw4R2","https://acortar.link/S539qm","https://acortar.link/VCX1Ch","https://acortar.link/IkGjbr","https://acortar.link/zYc8Pp","https://acortar.link/yPDyK4","https://acortar.link/n40ZDU","https://acortar.link/S5dlHu","https://acortar.link/YuBxzl","https://acortar.link/ynEfix","https://acortar.link/5g3VKG","https://acortar.link/rXIgry","https://acortar.link/YVxZPW","https://acortar.link/kBaqFf","https://acortar.link/RVvpt3","https://acortar.link/WLTP0S","https://acortar.link/I1LdP5","https://acortar.link/CASu9m"};
		Response<List<Publicacion>> response = new Response<>();
		List<Publicacion> listaPublicaciones = publicacionDao.findAll();
		for (int i=0;i<listaPublicaciones.size(); i++  ) {
			byte[] img = ConverterImagenes.convertURLtoBytes(imagenes[i]);
			listaPublicaciones.get(i).setImagenes(img);;
		}
		publicacionDao.saveAll(listaPublicaciones);
		response.setBody(listaPublicaciones);
		response.setStatus(HttpStatus.OK);
		return response;
	}
	
	@Override
	public Response<List<PublicacionResponse>> getAllAdmin() {
		List<PublicacionResponse> lista= new ArrayList<>();
		List<Publicacion> result=(List<Publicacion>)publicacionDao.findByAll(Estados.EnRevision.ordinal()+1);
		for(Publicacion item: result) 
		{
			lista.add(new PublicacionResponse(item.getId(), new UsuarioPublicacionResponse(item.getUsuario().getId(),StringUtils.armarNombre(item.getUsuario()),item.getUsuario().getPersona().getImagenes())
					,item.getNombre(),item.getDescripcion(),item.getCategoria().getDescripcion() ,item.getCondicion().getDescripcion(),StringUtils.armarUbicacion(item.getUbicacion()),StringUtils.armarUbicacion(item.getUbicacionPretendida()),
					item.getCategoriaPretendida().getDescripcion(),item.getImagenes()));
		}
		return new Response<List<PublicacionResponse>>( lista,HttpStatus.OK);
	}
	
	@Override
	public Response<?> updateAdmin(UpdatePublicacionAdminRequest request) {
 
        Response<?> response= new Response<>();	
		 
		 ////Valida id PUBLICACION
		 Optional<Publicacion> publicacion =publicacionDao.findById(request.id());
		if(publicacion.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Publicacion"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
			
			Optional<Estado> estado = estadoDao.findById(request.idEstado());
			if(estado.isEmpty()) {
			response.AddError("#1", "idEstado", String.format(ErrorMessage.NOTFOUND,request.idEstado(),"Estado"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
			} else {
				 Publicacion entity=publicacion.get();
				entity.setEstado(estado.get());
				publicacionDao.save(entity);
				response.setStatus(HttpStatus.OK);
			}			
				
		}
		
		return response;
	}
	
}

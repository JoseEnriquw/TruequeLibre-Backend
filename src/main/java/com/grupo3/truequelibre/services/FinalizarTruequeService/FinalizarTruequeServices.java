package com.grupo3.truequelibre.services.FinalizarTruequeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.grupo3.truequelibre.dao.IFinalizarTruequeDao;
import com.grupo3.truequelibre.dao.IOfertasDao;
import com.grupo3.truequelibre.entity.Estado;
import com.grupo3.truequelibre.entity.FinalizarTrueque;
import com.grupo3.truequelibre.entity.Oferta;
import com.grupo3.truequelibre.interfaces.IFinalizarTruequeServices;
import com.grupo3.truequelibre.responses.Oferta.FinalizarTruequeResponse;
import com.grupo3.truequelibre.services.OfertaService.UpdateOfertaRequest;
import com.grupo3.truequelibre.tools.ErrorMessage;
import com.grupo3.truequelibre.tools.Estados;
import com.grupo3.truequelibre.tools.Response;

public class FinalizarTruequeServices implements IFinalizarTruequeServices{
	@Autowired
	IFinalizarTruequeDao FinalizarTruequeDao;
	
	@Autowired
	IOfertasDao ofertaDao;
	

	@Override
	public Response<FinalizarTruequeResponse> GetByIdOferta(Integer id_oferta) {
		Optional<FinalizarTrueque> finalizar=null;	 
		
		finalizar=FinalizarTruequeDao.finByAll(id_oferta);
			
		
		Response<FinalizarTruequeResponse> response= new Response<>();
		if (finalizar == null || finalizar.isEmpty()) {
			response.AddError("#1", "","No ofertas were found with this filters");		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			FinalizarTruequeResponse content= new FinalizarTruequeResponse(
					finalizar.get().getId(),
					finalizar.get().getId_oferta().getId(),
					finalizar.get().getUsuario_principal_acepto(),
					finalizar.get().getUsuario_ofertante_acepto());
			
			response.setBody(content);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}
	
	@Override
	public Response<?> createfinalizarTrueque(CreateFinalizarTruequeRequest request) {
		Response<FinalizarTrueque> response= new Response<>();
		Optional<Oferta> oferta = ofertaDao.findById(request.idOferta());
		if (oferta.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.idOferta(),"Oferta"));
			response.setStatus(HttpStatus.NOT_FOUND);
		}else {
		FinalizarTrueque finalizartrueque=new FinalizarTrueque
			(oferta.get(), request.usuario_principal_acepto(), request.usuario_ofertante_acepto()
					);
		FinalizarTruequeDao.save(finalizartrueque);
				response.setStatus(HttpStatus.CREATED);
			}

		return response;
	}
	
	@Override
	public Response<?> update(UpdateFinalizarRequest request) {
		Optional<FinalizarTrueque>entity=FinalizarTruequeDao.findById(request.id());
		
		 Response<FinalizarTrueque> response= new Response<>();
		if(entity.isEmpty()) {
			response.AddError("#1", "id", String.format(ErrorMessage.NOTFOUND,request.id(),"Oferta"));		  
			response.setStatus(HttpStatus.NOT_FOUND);
		}
			else {
				
				FinalizarTrueque result=entity.get();
				result.setUsuario_principal_acepto(request.usuario_principal_acepto());
				result.setUsuario_ofertante_acepto(request.usuario_ofertante_acepto());	   
			
				FinalizarTruequeDao.save(result);
				response.setStatus(HttpStatus.OK);
			}
		
		return response;
	}
	

}

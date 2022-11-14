package com.grupo3.truequelibre.interfaces;

import com.grupo3.truequelibre.responses.Oferta.FinalizarTruequeResponse;
import com.grupo3.truequelibre.services.FinalizarTruequeService.CreateFinalizarTruequeRequest;
import com.grupo3.truequelibre.services.FinalizarTruequeService.UpdateFinalizarRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IFinalizarTruequeServices {
	
	Response<?> createfinalizarTrueque(CreateFinalizarTruequeRequest request);
	Response<FinalizarTruequeResponse> GetByIdOferta(Integer id_oferta);
	Response<?> update(UpdateFinalizarRequest request);

}

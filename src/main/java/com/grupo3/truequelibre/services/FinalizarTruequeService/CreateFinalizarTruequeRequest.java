package com.grupo3.truequelibre.services.FinalizarTruequeService;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record CreateFinalizarTruequeRequest(
		@NotNull
		  @Positive
		Integer idOferta,
		@NotNull		
		boolean usuario_principal_acepto,
		@NotNull		
		boolean usuario_ofertante_acepto
		) {

}

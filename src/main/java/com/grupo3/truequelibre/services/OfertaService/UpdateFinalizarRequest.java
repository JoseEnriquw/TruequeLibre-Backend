package com.grupo3.truequelibre.services.OfertaService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record UpdateFinalizarRequest(
		@NotNull
		@Positive
		Integer id,
		@NotNull		
		boolean usuario_principal_acepto,
		@NotNull		
		boolean usuario_ofertante_acepto
		) {

}

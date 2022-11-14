package com.grupo3.truequelibre.responses.Oferta;

public record FinalizarTruequeResponse(
		Integer id,
		boolean usuario_principal_acepto, 
		boolean usuario_ofertante_acepto
		) {

}

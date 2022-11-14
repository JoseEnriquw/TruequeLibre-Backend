package com.grupo3.truequelibre.responses.Oferta;

public record FinalizarTruequeResponse(
		Integer id,
		Integer id_oferta,
		boolean usuario_principal_acepto, 
		boolean usuario_ofertante_acepto
		) {

}

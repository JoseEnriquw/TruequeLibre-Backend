package com.grupo3.truequelibre.responses.Oferta;

public record FinalizarTruequeResponse(
		Integer id,
		Integer id_oferta,
		Boolean usuario_1_acepto, 
		Boolean usuario_2_acepto
		) {

}

package com.grupo3.truequelibre.responses.Oferta;

public record CalificarTruequeRequest(
		Integer id,
		boolean usuario_principal_califico, 
		boolean usuario_ofertante_califico
		) {

}

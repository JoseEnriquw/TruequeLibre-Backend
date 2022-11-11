package com.grupo3.truequelibre.responses.Oferta;

public record OfertaResponse(
		String nombre,
		String descripcion,
		byte [] imagen,
		Integer id_oferta		
		) {
	
}

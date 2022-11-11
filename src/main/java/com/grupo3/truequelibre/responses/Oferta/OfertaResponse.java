package com.grupo3.truequelibre.responses.Oferta;

public record OfertaResponse(
		String Nombre,
		String descripcion,
		byte [] Imagen,
		Integer id_oferta		
		) {
	
}

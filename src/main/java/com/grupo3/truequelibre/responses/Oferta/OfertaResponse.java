package com.grupo3.truequelibre.responses.Oferta;

public record OfertaResponse(
		String nombre_ofertante,
		String descripcion_ofertante,
		byte [] imagen_ofertante,
		String nombre_principal,
		String descripcion_principal,
		byte [] imagen_principal,
		Integer estado_id,
		Integer id_usuario_principal,
		Integer id
		) {
	
}

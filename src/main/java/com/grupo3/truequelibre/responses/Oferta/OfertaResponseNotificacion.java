package com.grupo3.truequelibre.responses.Oferta;

public record OfertaResponseNotificacion(
		Integer id_usuario_principal,
		String nombre_usuario_principal,
		byte[] imagen_usuario_principal,
		Integer id_usuario_ofertante,
		String nombre_usuario_ofertante,
		byte[] imagen_usuario_ofertante
		
		) {

}

package com.grupo3.truequelibre.responses.Publicacion;

import com.grupo3.truequelibre.responses.Usuario.UsuarioPublicacionResponse;

public record PublicacionResponse(
		Integer id,
	    UsuarioPublicacionResponse usuario,
	    String nombre,
	    String descripcion,
	    String categoria,
	    String condicion,
	    String ubicacion,
	    String ubicacionPretendida,
	    String interes,
	    byte[] imagenes
	    
		) {

}

package com.grupo3.truequelibre.responses.CalificacionUsuarios;

import java.util.Date;

public record CalificacionUsuariosResponse
        (
        		String nombreApellido,
        		byte[] imagen,
        		String comentario,
        		float estrellas,
        		Date fecha
		) {}

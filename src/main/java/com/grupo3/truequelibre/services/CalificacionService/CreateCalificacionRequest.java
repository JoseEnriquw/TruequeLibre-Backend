package com.grupo3.truequelibre.services.CalificacionService;

import javax.validation.constraints.NotNull;

public record CreateCalificacionRequest(
		@NotNull
		Integer idUsuarioCalificador,
		@NotNull
		Integer idUsuarioCalificado,
		@NotNull
		short estrellas,
		String comentario
		) {

}

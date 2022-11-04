package com.grupo3.truequelibre.services.CalificacionService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record FiltroCalificacionRequest(
		@NotNull
		Integer idUsuario,
		@NotNull
		@NotBlank
		String tipoCalificacion
		) {

}

package com.grupo3.truequelibre.services.OfertaService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record FiltrarOfertaRequest(
		@NotNull
		@Min(value=0)
		Integer id_estado,
		@NotNull
		@Min(value=0)
		Integer id_usuario
		) {

}

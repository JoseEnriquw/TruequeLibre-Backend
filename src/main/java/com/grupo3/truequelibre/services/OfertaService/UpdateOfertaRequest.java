package com.grupo3.truequelibre.services.OfertaService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public record UpdateOfertaRequest(
		@NotNull
		@Min(value=1)
		Integer id,
		@NotNull
		@Min(value=1)
		Integer id_estado
		) {

}

package com.grupo3.truequelibre.services.OfertaService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record CreateOfertaRequest(
		@NotNull
		@Min(value = 1)
		Integer id_publicacion_principal,
		@NotNull
		@Min(value = 1)
		Integer id_publicacion_ofertante) {

}

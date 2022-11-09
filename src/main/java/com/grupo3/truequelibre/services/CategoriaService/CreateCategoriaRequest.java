package com.grupo3.truequelibre.services.CategoriaService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateCategoriaRequest(
		@NotNull
		@NotBlank
		String descripcion,
		byte[] imagen
		
		) {

}

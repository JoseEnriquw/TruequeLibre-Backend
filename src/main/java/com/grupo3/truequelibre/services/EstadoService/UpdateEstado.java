package com.grupo3.truequelibre.services.EstadoService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateEstado(
		Integer id,
		@NotNull
		@NotBlank
		@Size(min = 1, max = 20)
		String descripcion) {

}

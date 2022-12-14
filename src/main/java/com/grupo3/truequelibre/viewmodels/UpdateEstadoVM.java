package com.grupo3.truequelibre.viewmodels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateEstadoVM(
		@NotNull
		@NotBlank
		@Size(min = 1, max = 20)
		String descripcion) {

}

package com.grupo3.truequelibre.services.CategoriaService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public record UpdateCategoriaRequest
        (
        		@NotNull
        		@Positive
        		Integer id,
        		@NotNull
        		@NotBlank
        		@Size(min = 1, max = 20)
        		String descripcion
		) {

}

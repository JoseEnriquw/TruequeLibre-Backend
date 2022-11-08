package com.grupo3.truequelibre.services.Categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CreateCategoriaRequest
        (
        		@NotNull
        		@NotBlank
        		@Size(min = 1, max = 50)
        		String descripcion
		) {

}

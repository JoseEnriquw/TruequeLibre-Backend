package com.grupo3.truequelibre.viewmodels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record UpdateOfertaVM(
		@NotNull
		@Min(value=1)
		Integer id_estado
		) {

}

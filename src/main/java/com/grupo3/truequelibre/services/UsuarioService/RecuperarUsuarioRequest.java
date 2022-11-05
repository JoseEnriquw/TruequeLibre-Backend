package com.grupo3.truequelibre.services.UsuarioService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RecuperarUsuarioRequest(
		@NotNull
		@NotBlank
		String email
		) {

}

package com.grupo3.truequelibre.services.UsuarioService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record LoginUsuarioRequest(
		@NotNull
		@NotBlank
		String email,
		@NotNull
		@NotBlank
		String contrasenia
		) {

}

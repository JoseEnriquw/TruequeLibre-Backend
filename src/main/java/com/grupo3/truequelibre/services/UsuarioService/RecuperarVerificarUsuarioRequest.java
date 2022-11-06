package com.grupo3.truequelibre.services.UsuarioService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record RecuperarVerificarUsuarioRequest(
		@NotNull
		@NotBlank
		@Size(min=6, max=6)
		String token,
		@NotNull
		@NotBlank
		@Size(min=8)
		String newPassword
		) {

}

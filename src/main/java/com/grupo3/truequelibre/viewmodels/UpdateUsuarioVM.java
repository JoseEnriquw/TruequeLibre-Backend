package com.grupo3.truequelibre.viewmodels;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateUsuarioVM(
		@NotNull
		@NotBlank
		String nombre,
		@NotNull
		@NotBlank
		String apellido,
		@NotNull
		@NotBlank
		String direccion,
		@NotNull
		@NotBlank
		String telefono,
		@NotNull
		Integer localidad,
		@NotNull
		Date fechaNacimiento,
		@NotNull
		@NotBlank
		@Size(min=8)
		String contrasenia
		) {

}

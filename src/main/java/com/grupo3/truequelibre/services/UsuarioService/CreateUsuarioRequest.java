package com.grupo3.truequelibre.services.UsuarioService;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public record CreateUsuarioRequest(
		@NotNull
		@NotBlank
		String mail,
		@NotNull
		@NotBlank
		@Size(min=8, max=12)
		String dni,
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
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		Date fechaNacimiento,
		@NotNull
		@NotBlank
		@Size(min=8)
		String contrasenia
		) {}

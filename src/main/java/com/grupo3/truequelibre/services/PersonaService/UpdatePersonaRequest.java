package com.grupo3.truequelibre.services.PersonaService;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record UpdatePersonaRequest(
		@NotNull
		@Positive
		Integer id,	
		@NotNull
		byte[] imagenes
		) {

}

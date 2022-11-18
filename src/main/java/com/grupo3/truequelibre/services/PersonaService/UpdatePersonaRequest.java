package com.grupo3.truequelibre.services.PersonaService;


import javax.validation.constraints.NotNull;

public record UpdatePersonaRequest(
		@NotNull		
		Integer idusuario,				
		byte[] imagenes
		) {

}

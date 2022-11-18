package com.grupo3.truequelibre.viewmodels;

import javax.validation.constraints.NotNull;

public record UpdatePersonaVM(
		@NotNull		
		Integer idusuario,				
		byte[] imagenes) {

}

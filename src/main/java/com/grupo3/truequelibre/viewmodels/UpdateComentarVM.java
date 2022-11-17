package com.grupo3.truequelibre.viewmodels;

import javax.validation.constraints.NotNull;

public record UpdateComentarVM(
		@NotNull		
		boolean usuario_principal_califico,
		@NotNull		
		boolean usuario_ofertante_califico
		) {

}

package com.grupo3.truequelibre.viewmodels;

import javax.validation.constraints.NotNull;

public record UpdateFinalizarVM
        (
        		@NotNull		
        		boolean usuario_principal_acepto,
        		@NotNull		
        		boolean usuario_ofertante_acepto
		) {}

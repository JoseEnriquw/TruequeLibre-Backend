package com.grupo3.truequelibre.viewmodels;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record UpdatePublicacionAdminVM
        (
		  @NotNull
		  @Positive
		  Integer idEstado
		) {}

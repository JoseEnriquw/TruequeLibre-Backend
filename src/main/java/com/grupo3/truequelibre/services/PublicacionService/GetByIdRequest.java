package com.grupo3.truequelibre.services.PublicacionService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record GetByIdRequest(
		@NotNull
		@Positive
		Integer id
		) {}

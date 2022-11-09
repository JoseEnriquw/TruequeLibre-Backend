package com.grupo3.truequelibre.services.PublicacionService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record GetAllByCategoriaFilterRequest
        (
     		   @NotNull
     		   @Positive
     		   Integer categoria,
     		   @NotNull
     		   @NotBlank
               String search		
        ) {}

package com.grupo3.truequelibre.services.PublicacionService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public record CreatePublicacionRequest
   (
	  @NotNull
	  @Positive
	  Integer idUsuario,
	  @NotNull
	  @NotBlank
	  @Size(min = 1, max = 255)
	  String nombre,
	  @NotNull
	  @NotBlank
	  @Size(min = 1)
	  String descripcion,
	  @NotNull
	  @Positive
	  Integer idCategoria,
	  @NotNull
	  @Positive
	  Integer idCategoriaPretendida,
	  @NotNull
	  @Positive
	  Integer idCondicion,
	  @NotNull
	  @Positive
	  Integer idUbicacion,
	  @NotNull
	  @Positive
	  Integer idUbicacionPretendida,
	  byte[] imagenes		   
	) {}

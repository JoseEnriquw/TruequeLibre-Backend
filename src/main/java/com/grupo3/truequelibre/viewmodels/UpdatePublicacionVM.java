package com.grupo3.truequelibre.viewmodels;

public record UpdatePublicacionVM
(
		  String nombre,

		  String descripcion,

		  Integer idCategoria,

		  Integer idCategoriaPretendida,

		  Integer idCondicion,

		  Integer idUbicacion,

		  Integer idUbicacionPretendida,

		  Integer idEstado,
		  byte[] imagenes	   
) {}
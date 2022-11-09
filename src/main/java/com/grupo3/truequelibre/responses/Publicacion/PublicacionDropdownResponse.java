package com.grupo3.truequelibre.responses.Publicacion;

import java.util.List;

import com.grupo3.truequelibre.responses.Categoria.CategoriaResponse;
import com.grupo3.truequelibre.responses.Condicion.CondicionResponse;
import com.grupo3.truequelibre.responses.Localidad.LocalidadResponse;


public record PublicacionDropdownResponse(
		List<CategoriaResponse> categorias,
		List<CondicionResponse> condiciones,
		List<LocalidadResponse> localidades
		) {

}

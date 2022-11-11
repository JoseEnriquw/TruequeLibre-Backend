package com.grupo3.truequelibre.responses.Usuario;

import java.util.List;

import com.grupo3.truequelibre.responses.Localidad.LocalidadResponse;

public record UsuarioDropdownResponse(List<LocalidadResponse> localidades) {
		
}

package com.grupo3.truequelibre.responses.Usuario;

public record UsuarioResponse
        (
        Integer id,
        String nombreApellido,
        String email,
        byte[] imagen	
		) {}

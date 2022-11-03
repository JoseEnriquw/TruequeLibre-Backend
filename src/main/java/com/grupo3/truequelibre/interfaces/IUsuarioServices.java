package com.grupo3.truequelibre.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.services.UsuarioService.CreateUsuarioRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IUsuarioServices {

	Response<List<Usuario>> getAll();
	Response<Usuario> getByEmail(String email);
	Response<Usuario> create(@Valid CreateUsuarioRequest usuario);
	
}

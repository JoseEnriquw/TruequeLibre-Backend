package com.grupo3.truequelibre.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.grupo3.truequelibre.entity.Usuario;
import com.grupo3.truequelibre.responses.Usuario.UsuarioDropdownResponse;
import com.grupo3.truequelibre.responses.Usuario.UsuarioResponse;
import com.grupo3.truequelibre.services.UsuarioService.CreateUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.LoginUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.RecuperarUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.RecuperarVerificarUsuarioRequest;
import com.grupo3.truequelibre.services.UsuarioService.UpdateUsuarioRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IUsuarioServices {

	Response<List<Usuario>> getAll();
	Response<Usuario> getByEmail(String email);
	Response<?> create(@Valid CreateUsuarioRequest usuario);
	Response<Usuario> update(@Valid UpdateUsuarioRequest usuario);
	Response<?> delete(String email);
	Response<UsuarioResponse> login(@Valid LoginUsuarioRequest request);
	Response<?> recuperar(@Valid RecuperarUsuarioRequest request);
	Response<?> recuperarVerificar(@Valid RecuperarVerificarUsuarioRequest request);
	Response<UsuarioDropdownResponse> getDataDropdown();
	
	Response<?> cargarImagenes();
}

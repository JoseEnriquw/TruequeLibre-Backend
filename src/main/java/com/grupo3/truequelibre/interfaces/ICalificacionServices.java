package com.grupo3.truequelibre.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.grupo3.truequelibre.entity.CalificacionUsuarios;
import com.grupo3.truequelibre.responses.CalificacionUsuarios.CalificacionUsuariosResponse;
import com.grupo3.truequelibre.services.CalificacionService.CreateCalificacionRequest;
import com.grupo3.truequelibre.services.CalificacionService.FiltroCalificacionRequest;
import com.grupo3.truequelibre.tools.Response;

public interface ICalificacionServices {

	Response<List<CalificacionUsuariosResponse>> getAll(Integer idUsuario);
	Response<CalificacionUsuarios> create(@Valid CreateCalificacionRequest request);
	Response<List<CalificacionUsuarios>> getByTipo(@Valid FiltroCalificacionRequest request);

}

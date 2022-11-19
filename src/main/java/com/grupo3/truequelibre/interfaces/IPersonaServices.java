package com.grupo3.truequelibre.interfaces;

import javax.validation.Valid;

import com.grupo3.truequelibre.responses.Persona.PersonaResponse;
import com.grupo3.truequelibre.services.PersonaService.UpdatePersonaRequest;
import com.grupo3.truequelibre.tools.Response;

public interface IPersonaServices {

	Response<PersonaResponse> getById(Integer id);

	Response<?> updateImg(@Valid UpdatePersonaRequest request);

}

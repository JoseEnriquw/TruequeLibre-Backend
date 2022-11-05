package com.grupo3.truequelibre.services.OfertaService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.grupo3.truequelibre.tools.ValidateString;

public record FiltrarOfertaRequest(
		@NotNull
		@Positive
		@Min(3)
		@Max(4)
		Integer id_estado,
		@NotNull
		@Positive
		Integer id_usuario,
		@NotNull
		@NotBlank
		@ValidateString(acceptedValues={"Enviados", "Recibidos","Aceptados"}, message="El estado enviado es invalido, use {'Enviados','Recibidos','Aceptados'}")
		String estado
		) {

}

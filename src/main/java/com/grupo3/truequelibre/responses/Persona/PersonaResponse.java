package com.grupo3.truequelibre.responses.Persona;

import java.util.Date;


import com.grupo3.truequelibre.entity.Localidad;
import com.grupo3.truequelibre.entity.Usuario;

public record PersonaResponse(
		 String dni,		
		 String nombre,		
		 String apellido,		
		 String direccion,		
		 Date fechaNacimiento,		
		 String telefono,		
		 Localidad localidad,	  
	     byte[] imagenes
		) {

}

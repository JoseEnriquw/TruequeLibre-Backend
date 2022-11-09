package com.grupo3.truequelibre.tools;

import com.grupo3.truequelibre.entity.Localidad;
import com.grupo3.truequelibre.entity.Usuario;

public class StringUtils {
    public static String armarNombre(Usuario usuario)
    {
    	return usuario.getPersona().getNombre()+" "+usuario.getPersona().getApellido();
    }
    
    public static String armarUbicacion(Localidad ubicacion)
    {
    	return ubicacion.getNombre()+", "+ubicacion.getProvincia().getNombre();
    }
}

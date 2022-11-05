package com.grupo3.truequelibre.tools;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import com.grupo3.truequelibre.entity.SeguridadUsuario;
import com.grupo3.truequelibre.entity.Usuario;

public class SeguridadTools {
		private static final String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		public static SeguridadUsuario generarSeguridadUsuario(Usuario usuario) {
			String codigoAleatorio = cadenaAleatoria(6);
			SeguridadUsuario seg = new SeguridadUsuario();
			seg.setUsuario(usuario);
			seg.setToken(codigoAleatorio);
			seg.setExpira(LocalDateTime.now().plusMinutes(30));
			
			return seg;
		}
		
		public static String cadenaAleatoria(int longitud) {
		    String cadena = "";
		    for (int x = 0; x < longitud; x++) {
		        int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
		        char caracterAleatorio = banco.charAt(indiceAleatorio);
		        cadena += caracterAleatorio;
		    }
		    return cadena;
		}
		
		public static int numeroAleatorioEnRango(int minimo, int maximo) {
		    return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
		}
}

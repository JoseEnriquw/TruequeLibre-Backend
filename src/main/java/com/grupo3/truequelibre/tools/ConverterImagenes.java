package com.grupo3.truequelibre.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ConverterImagenes {
	public static byte[] convertURLtoBytes(String url) {
		URL u;
		byte[] imagen = null;
		try {
			u = new URL(url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = null;
			try {
			  is = u.openStream ();
			  byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
			  int n;

			  while ( (n = is.read(byteChunk)) > 0 ) {
			    baos.write(byteChunk, 0, n);
			  }
			}
			catch (IOException e) {
			  System.err.printf ("Failed while reading bytes from %s: %s", u.toExternalForm(), e.getMessage());
			  e.printStackTrace ();
			  // Perform any other exception handling that's appropriate.
			}
			finally {
			  if (is != null) { is.close(); }
			  imagen = baos.toByteArray();
			  
			  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imagen;
	}
}

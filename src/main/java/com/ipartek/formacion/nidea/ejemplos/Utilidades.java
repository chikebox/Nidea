package com.ipartek.formacion.nidea.ejemplos;

public class Utilidades {
	/**
	 * Metodo estático para poder usarse desde la propia Clase, sin tener que instanciar un objeto
	 * 
	 * Limpia los espacios en blanco de una cadena String
	 * Hacemos trim, además de sustituir todos los espacios en blanco que encuentre por 1 unico ej:
	 * 
	 * "           coche         rojo        " => "coche rojo"
	 * @param cadena
	 * @return
	 */
	 
	public static String limpiarEspacios(String cadena) {
		if(cadena==null) {
			return "";
		}
		cadena= cadena.trim();
		cadena=cadena.replaceAll("\\s+"," ");
		return cadena;
	}
}

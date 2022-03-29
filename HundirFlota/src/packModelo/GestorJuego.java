package packModelo;

import java.util.Observable;

public class GestorJuego extends Observable {
	private static GestorJuego miGestor;
	
	
	private GestorJuego() {}
	public static GestorJuego getMiGestorJuego() {
		if (miGestor==null) {
			miGestor=new GestorJuego();
		}return miGestor;
	}
	
	
}

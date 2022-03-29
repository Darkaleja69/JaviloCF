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
	
	//Metodos
	public void colocarBarcos()
	{
		Casilla[] casillas = new Casilla[4]; //4 es provisional
		//actualizar vista
		setChanged();
		notifyObservers(casillas);
	}
	
}

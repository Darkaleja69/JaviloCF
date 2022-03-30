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
	public void colocarBarcos(boolean pHorizontal, int pX, int pY)
	{
		Casilla[] casillas = new Casilla[4];
		
		Tablero.getTablero()GestorJuego.todoValido();
		
		
		//actualizar vista
		setChanged();
		notifyObservers(casillas);
	}
	
}

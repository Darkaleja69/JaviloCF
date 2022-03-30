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
	public void colocarBarcos(boolean pHorizontal, int pX, int pY, int pLongitud, boolean pEsJugador)
	{
		Casilla[] casillas = new Casilla[4];
		boolean esjugador;
		if(Tablero.getTablero().todoValido(pX, pY, pLongitud, true, pHorizontal))
		{
			
		}
		
		//actualizar vista
		setChanged();
		notifyObservers(casillas);
	}
	
}

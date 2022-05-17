package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GestorJuego extends Observable {
	private static GestorJuego miGestor;
	
	private GestorJuego() 
	{
	}
	public static GestorJuego getMiGestorJuego() {
		if (miGestor==null) 
		{
			miGestor=new GestorJuego();
		}
		return miGestor;
	}
	
	//Metodos
	public void colocarBarcos(boolean pHorizontal, int pX, int pY, int pLongitud)
	{
		ListaJugadores.getMiListaJug().colocarBarcos(pHorizontal, pX, pY, pLongitud);
	}
	public boolean comprobarFin(boolean pJug) {
		return ListaJugadores.getMiListaJug().comprobarFin(pJug);
	}
	
	public ArrayList<Casilla> marcarBarcoHundido(Casilla pCasilla,boolean pJug) {
		return Tablero.getTablero().obtenerCasillasBarco(pCasilla, pJug);
	}
	
	public int barcosPorColocar(int pLong) {
		return ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).barcosPorColocar(pLong);
	}
	
	
	public int dineroRestanteJug() {
		return ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).dineroRestante();
	}
	
	public void barcosColocadosTienda() {
		boolean colocados=true;

			setChanged();
			notifyObservers(colocados);
	}
	
	public boolean barcosColocados() {
		return ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).barcosColocados();
	}

	public void jugarTurno(int pOpcion,int pX,int pY) {
		ListaJugadores.getMiListaJug().realizarTurno(pOpcion, pX, pY);
	}
	
	public void realizarTurno(int pOpcion,int pX,int pY) {
		ListaJugadores.getMiListaJug().realizarTurno(pOpcion, pX, pY);
	}
	
	public int armasPorUsar(int pOpcion) {
		return ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).armasEnArmamento(pOpcion);
	}
	
	public void comprarArmamento(int pOpcion) {
		ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).comprarArmamento(pOpcion);
	}
		
	
}

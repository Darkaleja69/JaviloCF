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
		if(Jugador.getJugador().colocarBarcos(pHorizontal, pX, pY, pLongitud))
		{
			CPU.getMiCPU().colocarBarco(pLongitud);
		}
	}
	
	public void colocarEscudo(int pX, int pY)
	{
		boolean fin = false;
		int jugadorOCPU = 1;

		Jugador.getJugador().colocarEscudo(pX, pY);
		//Turno CPU
		CPU.getMiCPU().turnoCPU();
	}
	
	public void disparar(int pX, int pY) 
	{
		boolean fin = false;
		int jugadorOCPU = 1;
		boolean disparado = Jugador.getJugador().disparar(pX, pY);
		
		if(disparado) {
			if(comprobarFin(false)) 
			{
				fin = true;
			
			}
			else //Turno CPU
			{
				CPU.getMiCPU().turnoCPU();
			}
		}
		if(fin) 
		{	
			setChanged();
			notifyObservers(jugadorOCPU);
		}
	}
	
	public void misil(int pX, int pY) 
	{
		boolean fin = false;
		int jugadorOCPU = 1;
		boolean disparado = Jugador.getJugador().misil(pX, pY);
		
		if(disparado) {
			if(comprobarFin(false)) 
			{
				fin = true;
			
			}
			else //Turno CPU
			{
				CPU.getMiCPU().turnoCPU();
			}
		}
		if(fin) 
		{	
			setChanged();
			notifyObservers(jugadorOCPU);
		}
	}
	
	public void radar() {
		
		boolean turno = Jugador.getJugador().radar();
		
		if(turno) {
			CPU.getMiCPU().turnoCPU();
		}
	}
	
	
	public boolean comprobarFin(boolean pJug) {
		boolean fin=true;
		if(pJug) 
		{
			fin=Jugador.getJugador().comprobarFin();
		}
		else 
		{
			fin=CPU.getMiCPU().comprobarFin();
		}
		return fin;
	}
	
	public ArrayList<Casilla> marcarBarcoHundido(Casilla pCasilla,boolean pJug) {
		return Tablero.getTablero().obtenerCasillasBarco(pCasilla, pJug);
	}
	
	public int barcosPorColocar(int pLong) {
		return Jugador.getJugador().barcosPorColocar(pLong);
	}
	
	public int escudosPorColocar() {
		return Jugador.getJugador().escudosPorColocar();
	}
	
	public int radaresPorColocar() {
		return Jugador.getJugador().cantidadRadares();
	}
	
	public int dineroRestanteJug() {
		return Jugador.getJugador().dineroRestante();
	}
}

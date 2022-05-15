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
	
/*	public void colocarEscudo(int pX, int pY)
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
*/
	
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
	
	
	public int dineroRestanteJug() {
		return Jugador.getJugador().dineroRestante();
	}
	
	public void barcosColocadosTienda() {
		boolean colocados=true;

			setChanged();
			notifyObservers(colocados);
	}
	
	public boolean barcosColocados() {
		return Jugador.getJugador().barcosColocados();
	}
	
/*	public void repararBarco(int pFila, int pCol)
	{
		
		Jugador.getJugador().enviarCasillas(lCasillas);
	}
*/	
	public void turnoJugador(int pOpcion,int pX,int pY) {
		boolean fin = false;
		int jugadorOCPU = 1;
		if(Jugador.getJugador().turnoJugador(pOpcion, pX, pY)) {
			
				if(comprobarFin(false)) 
				{	
						setChanged();
						notifyObservers(jugadorOCPU);
				}else {
					CPU.getMiCPU().turnoCPU();
				}
				
		}
				
	}
	
	public int armasPorUsar(int pOpcion) {
		return Jugador.getJugador().armasEnArmamento(pOpcion);
	}
	
	public void comprarArmamento(int pOpcion) {
		Jugador.getJugador().comprarArmamento(pOpcion);
	}
		
	
}

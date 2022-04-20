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
		if (Jugador.getJugador().escudosSuficientes())
		{
			Jugador.getJugador().colocarEscudo(pX, pY);
		}
	}
	
	public void disparar(int pX, int pY) 
	{
		boolean fin = false;
		int jugadorOCPU=1;
		boolean disparado = Jugador.getJugador().disparar(pX, pY);
		
		if(disparado) {
			if(comprobarFin(false)) 
			{
				fin = true;
			
			}
			else 
			{
				Random x = new Random();
				boolean escudo = x.nextBoolean();
				if (CPU.getMiCPU().escudosSuficientes() && escudo)
				{
					CPU.getMiCPU().colocarEscudo();
				}
				//Disparo CPU
				CPU.getMiCPU().dispararInteligente();
				if(comprobarFin(true)) 
				{
					fin = true;
					jugadorOCPU=2;
					
				}
			}
			
		}
			if(fin) 
			{	
				
				setChanged();
				notifyObservers(jugadorOCPU);
			}
		}
	
	public void radar() {
		Random num1 = new Random();	
		Random num2 = new Random();	
		int x = num1.nextInt(7)+2;
		int y = num2.nextInt(7)+2;
		Jugador.getJugador().radar(x, y);
		
	}
	
	private boolean comprobarFin(boolean pJug) {
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
}

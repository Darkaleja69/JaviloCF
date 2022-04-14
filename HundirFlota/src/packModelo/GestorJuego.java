package packModelo;

import java.util.ArrayList;
import java.util.Observable;

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
	
	public void disparar(int pX,int pY) 
	{
		boolean fin = false;
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		boolean disparado = Jugador.getJugador().disparar(pX, pY);
		
		if(disparado) {
			if(comprobarFin(false)) 
			{
				fin = true;
			}
			else 
			{
				//Disparo CPU
				casillas.addAll(CPU.getMiCPU().disparar());
				if(comprobarFin(true)) 
				{
					fin = true;
				}
			}
			
		}
			setChanged();
			notifyObservers(casillas);
			if(fin) 
			{
				
				setChanged();
				notifyObservers(fin);
			}
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
}

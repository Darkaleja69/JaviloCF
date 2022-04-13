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
	public void colocarBarcosJug(boolean pHorizontal, int pX, int pY, int pLongitud)
	{
		if(!Jugador.getJugador().hayDemasiados(pLongitud))
		{
			ArrayList<Casilla> casillas = new ArrayList<Casilla>();
			
			if(Tablero.getTablero().todoValido(pX, pY, pLongitud, true, pHorizontal))
			{
				Barco b = FactoryBarcos.getMiFactoryBarcos().crearBarco(pLongitud);
				Jugador.getJugador().anadirBarco(b);
				casillas = Tablero.getTablero().colocarBarco(b, pX, pY, pLongitud, pHorizontal,true);
				CPU.getMiCPU().anadirBarco(CPU.getMiCPU().colocarBarco(pLongitud));
			}
			//actualizar vista
			setChanged();
			notifyObservers(casillas);
		}
	}
	
	public void disparar(int pX,int pY) 
	{	boolean fin=false;
		ArrayList<Casilla> casillas=new ArrayList<Casilla>();
		Casilla x=Jugador.getJugador().disparar(pX, pY);
		if(x!=null) {
			casillas.add(x);
		
			if(comprobarFin(false)) 
			{
				fin=true;
			}
			else 
			{
				//Disparo CPU
				casillas.addAll(CPU.getMiCPU().disparar());
				if(comprobarFin(true)) 
				{
					fin=true;
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
	
	public boolean barcosColocados()
	{
		return Jugador.getJugador().barcosColocados();
	}
	
}

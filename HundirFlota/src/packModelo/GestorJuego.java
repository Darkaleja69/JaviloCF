package packModelo;

import java.util.ArrayList;
import java.util.Observable;

public class GestorJuego extends Observable {
	private static GestorJuego miGestor;
	private ListaBarcos lBarcJugador;
	private ListaBarcos lBarcCPU;
	
	private GestorJuego() 
	{
		lBarcJugador = new ListaBarcos();
		lBarcCPU= new ListaBarcos();
	}
	public static GestorJuego getMiGestorJuego() {
		if (miGestor==null) {
			miGestor=new GestorJuego();
		}return miGestor;
	}
	
	//Metodos
	public void colocarBarcosJug(boolean pHorizontal, int pX, int pY, int pLongitud)
	{
		if(!lBarcJugador.hayDemasiados(pLongitud))
		{
			ArrayList<Casilla> casillas = new ArrayList<Casilla>();
			
			if(Tablero.getTablero().todoValido(pX, pY, pLongitud, true, pHorizontal))
			{
				Barco b = FactoryBarcos.getMiFactoryBarcos().crearBarco(pLongitud);
				lBarcJugador.anadirBarco(b);
				casillas = Tablero.getTablero().colocarBarco(b, pX, pY, pLongitud, pHorizontal,true);
				CPU.getMiCPU().colocarBarco(pLongitud);
			}
			//actualizar vista
			setChanged();
			notifyObservers(casillas);
		}
	}
	
	public void disparar(int pX,int pY) {
		ArrayList<Casilla> casillas=new ArrayList<Casilla>();
		
		
		
		setChanged();
		notifyObservers(casillas);
	}
	
	
	
	
	
}

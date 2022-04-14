package packModelo;

import java.util.ArrayList;
import java.util.Observable;

public class Jugador extends Observable {
	private static Jugador miJugador;
	private ListaBarcos lista;
	
	private Jugador() {
		lista=new ListaBarcos();
	}
	
	public static Jugador getJugador() {
		if(miJugador==null) {
			miJugador=new Jugador();
		}
		return miJugador;
	}
	
	public Casilla disparar(int pX,int pY) {
		Casilla b = null;
		Casilla x = Tablero.getTablero().getCasilla(pX, pY, false);
		if(!x.estaTocada()) 
		{
			b = x;
			Tablero.getTablero().bombardear(pX, pY, false);
		}
		return b;
	}
	
	public boolean colocarBarcos(boolean pHorizontal, int pX, int pY, int pLongitud){
		ArrayList<Casilla> casillas = new ArrayList<Casilla>();
		if(!hayDemasiados(pLongitud)) {
			
			if(Tablero.getTablero().todoValido(pX, pY, pLongitud, true, pHorizontal))
			{
				Barco b = FactoryBarcos.getMiFactoryBarcos().crearBarco(pLongitud);
				anadirBarco(b);
				casillas = Tablero.getTablero().colocarBarco(b, pX, pY, pLongitud, pHorizontal,true);
			}
		}
		setChanged();
		notifyObservers(casillas);
		return !(casillas.size() < 1);
	}
	
	public boolean hayDemasiados(int pLong) {
		return lista.hayDemasiados(pLong);
	}
	
	public void anadirBarco(Barco pBarco) {
		this.lista.anadirBarco(pBarco);
	}
	
	public boolean comprobarFin() {
		return this.lista.comprobarFin();
	}
	
	public boolean barcosColocados() {
		return this.lista.lleno();
	}
	
	
}

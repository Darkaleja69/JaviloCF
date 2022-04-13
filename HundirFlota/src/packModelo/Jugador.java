package packModelo;

import java.util.ArrayList;

public class Jugador {
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
		Casilla b=null;
		Casilla x=Tablero.getTablero().getCasilla(pX, pY, false);
		if(!x.estaTocada()) 
		{
			b=x;
			Tablero.getTablero().bombardear(pX, pY, false);
		}
		return b;
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

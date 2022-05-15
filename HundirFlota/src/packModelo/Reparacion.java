package packModelo;

import java.util.ArrayList;

public class Reparacion extends Arma{
	
	
	public Reparacion() {
		coste=50;
	}
	@Override
	public boolean realizarFuncion(int pX,int pY, boolean pAQuien) {
		
		Casilla cas = Tablero.getTablero().getCasilla(pX, pY, !pAQuien);
		ArrayList <Casilla> lCasillas = Tablero.getTablero().obtenerCasillasBarco(cas, !pAQuien);
		cas.getBarco().reparar();
		for (Casilla c: lCasillas)
		{
			c.reparar();
		}
		Jugador.getJugador().enviarCasillas(lCasillas);
		return true;
	}
}

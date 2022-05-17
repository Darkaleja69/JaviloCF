package packModelo;

import java.util.ArrayList;

public class Reparacion extends Arma{
	private int casillas;
	
	public Reparacion() {
		casillas=1;
		coste=50;
	}
	@Override
	public boolean realizarFuncion(int pX,int pY, boolean pAQuien) {
		boolean listo=false;
		Casilla cas = Tablero.getTablero().getCasilla(pX, pY, true);
		ArrayList <Casilla> lCasillas = Tablero.getTablero().obtenerCasillasBarco(cas, true);
		if(casillas >=lCasillas.size()) {
			cas.getBarco().reparar();
			for (Casilla c: lCasillas)
			{
				c.reparar();
				
			}
			casillas=casillas-lCasillas.size();
			listo=true;
		}
		ListaJugadores.getMiListaJug().obtenerJugadorOCPU(1).enviarCasillas(lCasillas);
		return listo;
	}
	
	public void sumarCasilla() {
		
		casillas++;
		
	}
	
	public int getCasillas() {
		return casillas;
	}
}

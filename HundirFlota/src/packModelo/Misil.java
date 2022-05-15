package packModelo;

import java.util.ArrayList;

public class Misil extends Arma{
	
	public Misil() {
		coste=100;
	}
	
	public boolean realizarFuncion(int pX,int pY, boolean pAQuien) {
		boolean disparado = false;
		Casilla b = null;
		Casilla x = Tablero.getTablero().getCasilla(pX, pY, pAQuien);
		
			if(!x.estaTocada()) 
			{
				b = x;
				Tablero.getTablero().lanzarMisil(pX, pY, pAQuien);
				ArrayList<Casilla> casillas = new ArrayList<Casilla>();
				casillas.add(b);
				disparado = true;
				if(pAQuien) {
					Jugador.getJugador().enviarCasillas(casillas);
				}else {
				CPU.getMiCPU().enviarCasillas(casillas);
				}	
			}
		
		return disparado;
	}

}

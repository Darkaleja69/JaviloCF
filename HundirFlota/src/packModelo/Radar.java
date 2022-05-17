package packModelo;

import java.util.ArrayList;
import java.util.Random;

public class Radar extends Arma{
	private Casilla r;
	private int radares; 
	
	public Radar (int pRadares) {
		r = null;
		radares = pRadares;
		super.coste = 100;
		
	}
	
	public boolean realizarFuncion(int pX,int pY, boolean pAQuien) {
		//para los turnos, que el metodo devuelva un booleano para saber si ha funcionado y q haga la CPU su movimiento
			boolean sig = false;
			ArrayList<Casilla> casillas = new ArrayList<Casilla>();
			if(pX < 0 && pY < 0 || r==null) { //se quiere recolocar el radar
				r = recolocar();
				casillas = Tablero.getTablero().colocarRadar(r.getFila(), r.getColumna(), pAQuien);
			}
			else{//se quiere activar el radar
				sig = true;
				casillas = Tablero.getTablero().detectar(pX, pY, pAQuien);
			}
			CPU.getMiCPU().enviarCasillas(casillas);
			return(sig);
	}
	
	public Casilla recolocar() {
		super.usado();
		Random num1 = new Random();	
		Random num2 = new Random();	
		int x = num1.nextInt(7)+2;
		int y= num2.nextInt(7)+2;
	    Casilla c = new Casilla(x,y);
	    return(c);
		
	}
	public Casilla getRadar() {
		return r;
	}
	public int cantRadares() {
		return radares;
	}
}

package packModelo;

import java.util.ArrayList;
import java.util.Random;

public class Radar extends Arma{
	
	public boolean realizarFuncion(int pX,int pY, boolean pAQuien) {
		//para los turnos, que el metodo devuelva un booleano para saber si ha funcionado y q haga la CPU su movimiento
		
			ArrayList<Casilla> casillas = new ArrayList<Casilla>();
			Random num1 = new Random();	
			Random num2 = new Random();	
			int x = num1.nextInt(7)+2;
			int y= num2.nextInt(7)+2;
			int fmax = x +1;
			int cmax = y +1;
					
			Casilla c = null;
				
			for(int i = x -1;i<=fmax;i++) {	
				
				for(int j = y -1;j<=cmax;j++) {
						
					c = Tablero.getTablero().getCasilla(i, j, false);
					c.ponerRadar();
					casillas.add(c);
				}
					
			}
				
			CPU.getMiCPU().enviarCasillas(casillas);
				
			return(true);
	}
	
	public void recolocar() {}
}
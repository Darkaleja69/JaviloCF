package packModelo;

import java.util.ArrayList;

public class Armamento {
	private ArrayList<Escudo> escudos;
	//private ArrayList<Misil> misiles;
	//private ArrayList<Bomba> bombas;
	
	public Armamento() {
		escudos=new ArrayList<Escudo>();
	}
	
	
	public boolean consultaArmamento(int pOpcion, int pDinero) {
		boolean posible=false;
		if(pOpcion==1) { //misil
			if(pDinero>=100) {
				posible=true;
			}
		}else if(pOpcion==2) { //escudo
			if(pDinero>=50) {
				posible=true;
			}
		}
		
		return posible;
	}
}

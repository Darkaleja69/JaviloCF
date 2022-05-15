package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Armamento {
	private ArrayList<Arma> armas;
	
	public Armamento() {
		armas=new ArrayList<Arma>();
	}
	
	
	public boolean consultaArmamento(int pOpcion, int pDinero) {
		boolean posible=false;
		if(pOpcion==1) { //misil
			if(pDinero>=100) {
				posible=true;
			}
		}else if(pOpcion==2 || pOpcion==3) { //escudo y reparacion de barco
			if(pDinero>=50) {
				posible=true;
			}
		}
		return posible;
	}
	
	public Arma comprarArmamento(String pTipo) {
		return FactoriaArmas.getMiFactoria().crearArma(pTipo);
	}
	
	public void anadirArmamento(Arma pArma) {
		armas.add(pArma);
	}
	
	public boolean buscarArma(Arma pArma) {
		Iterator<Arma> itr=this.getIterator();
		Arma x=null;
		boolean enc=false;
		String nombre=null;
		if(pArma instanceof Escudo) {
			nombre="Escudo";
		}else if(pArma instanceof Bomba) {
			
		}
		while(itr.hasNext() && !enc) {
			x=itr.next();
			if(x.equals(pArma)) {
				enc=true;
			}
		}
		return enc;
	}
	
	private Iterator<Arma> getIterator(){
		return armas.iterator();
	}
}
